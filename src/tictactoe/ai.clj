(ns tictactoe.ai
  (:require [tictactoe.board :refer [winner full? available-spaces place-move]]))

(defn random-ai-move [board] 
  (rand-nth (available-spaces board)))

(defn abs [n]
  (if (< n 0)
    (- 0 n)
    n))

(defn set-max [piece]
  {:strategy :max :piece piece :starting-score -5})

(defn set-min [piece]
  {:strategy :min :piece piece :starting-score 5})

(defn win [player board depth]
  (if (= (:piece player) (winner board))
    (if (= :max (:strategy player))
      (- 100 depth)
      (+ -100 depth))))

(defn loss [opponent board depth]
  (if (= (:piece opponent) (winner board))
    (if (= :max (:strategy opponent))
      (- 100 depth)
      (+ -100 depth))))

(defn draw [board]
  (if (full? board) 0))

(defn value [board player opponent depth]
  (or (win player board depth)
      (loss opponent board depth)
      (draw board)))

(defn best-score [player values]
  (let [all-values (conj values (:starting-score player))]
    (if (= :max (:strategy player))
      (apply max all-values)
      (apply min all-values))))

(defn minimax-score [player opponent board depth]
  (or (value board player opponent depth)
      (let [values (map #(minimax-score opponent player 
                                        (place-move % (:piece opponent) board) (inc depth)) 
                     (available-spaces board))]
        (best-score opponent values))))

(defn best-move [move-values]
  (first (first (sort-by val > move-values))))

(defn minimax-move [maxplayer minplayer board]
  (let [values (map #(minimax-score maxplayer minplayer 
                                    (place-move % (:piece maxplayer) board) 1) 
                    (available-spaces board))
        moves (available-spaces board)
        move-values (zipmap moves values)]
    (best-move move-values)))

(defn ai-move [max-piece min-piece board]
  (minimax-move (set-max max-piece) (set-min min-piece) board))
