(ns tictactoe.ai
  (:require [tictactoe.board :refer [winner full? available-spaces place-move]]))

(defn random-ai-move [board] 
  (rand-nth (available-spaces board)))

(defn ai-move [p1-symbol p2-symbol board]
  (random-ai-move board))


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
  (if (= opponent (winner board))
    (if (= :max (:strategy opponent))
      (- 100 depth)
      (+ -100 depth))))

(defn draw [board]
  (if (full? board) 0))

(defn value [board player opponent depth]
  (or (win player board depth)
      (loss opponent board depth)
      (draw board)))
 
;(defn minimax-score 
  ;([max-piece min-piece board depth] (minimax-score (set-max max-piece) (set-min min-piece) 
                                                    ;board (available-spaces board)
                                                    ;depth))
  ;([player opponent board spaces depth]
        ;(let [spaces (available-spaces board)
              ;new-board (place-move (first spaces) (:piece player) board)]
              ;(minimax-score opponent player new-board (rest spaces) (inc depth)))))

(defn minimax-score 
  ([max-player min-player board depth] (minimax-score (set-max max-player) (set-min min-player)
                                                      board (available-spaces board)
                                                      depth))
  ([player opponent board spaces depth]
    (or (value board player opponent depth)
        (let [new-board (place-move (first spaces) (:piece player) board)]
          (or (value new-board player opponent (inc depth))
              (minimax-score opponent player new-board (rest spaces) (inc depth)))))))

(defn minimax-move 
  ([p1_symbol p2_symbol board] (minimax-move p1_symbol p2_symbol board (available-spaces board) {}))
  ([p1_symbol p2_symbol board spaces move-values]
   (if-not (empty? spaces)
     (let [depth 0
           new-board (place-move (first spaces) p1_symbol board)
           new-values (merge move-values {(first spaces) (minimax-score p1_symbol p2_symbol new-board (inc depth))})]
      (recur p1_symbol p2_symbol board (rest spaces) new-values))
     (println move-values))))

