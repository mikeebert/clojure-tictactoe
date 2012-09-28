(ns tictactoe.ai
  (:require [tictactoe.board :refer [winner full? available-spaces place-move]]))

(defn random-ai-move [board] 
  (rand-nth (available-spaces board)))

(defn ai-move [p1-symbol p2-symbol board]
  (random-ai-move board))

(defn abs [n]
  (if (< n 0)
    (- 0 n)
    n))

(defn set-max [piece]
  {:strategy :max :piece piece :starting-score -5 :best-score -5})

(defn set-min [piece]
  {:strategy :min :piece piece :starting-score 5 :best-score 5})

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

(defn minimax-score [player opponent board depth]
  (or (value board player opponent depth)
      (if-not (empty? (available-spaces board))
        (let [best-score (:starting-score player)
              spaces (available-spaces board)
              new-board (place-move (first spaces) (:piece opponent) board)
              game-value (minimax-score opponent player new-board (inc depth))]
          (if (> (abs game-value) (abs (opponent :best-score)))
            (merge opponent {:best-score game-value})
          (if (empty? (available-spaces board))
            (do (println "it got here") (:best-score opponent))
            (recur opponent player new-board (+ 2 depth))))))))

(defn minimax-move 
  ([p1-symbol p2-symbol board] (minimax-move p1-symbol p2-symbol board 
                                             (available-spaces board) {}))
  ([p1-symbol p2-symbol board spaces move-values]
   (let [max-player (set-max p1-symbol)
         min-player (set-min p2-symbol)] 
     (if-not (empty? spaces)
       (let [depth 0
             move (first spaces)
             new-board (place-move move p1-symbol board)
             new-values (merge move-values {move (minimax-score max-player min-player 
                                                                new-board (inc depth))})]
        (recur p1-symbol p2-symbol board (rest spaces) new-values))
       (println move-values)))))

