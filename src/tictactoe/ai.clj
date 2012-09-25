(ns tictactoe.ai
  (:require [tictactoe.board :refer [winner full? available-spaces place-move]]))

(defn random-ai-move [board] 
  (rand-nth (available-spaces board)))

(defn ai-move [p1-symbol p2-symbol board]
  (random-ai-move board))

;(defn minimax_move [p1_symbol p2_symbol board]
  ;(let [spaces-index 0
        ;alpha -100
        ;beta 100
        ;depth 0
        ;best-score-for-max -5
        ;best-score-for-min 5
        ;possible-moves []
        ;new-board (place-move (nth (available-spaces board) spaces-index) p1_symbol board)
        ;score (minimax_score new-board p1_symbol alpha beta (inc depth))]
    ;(if (= score best-score-for-max)
      ;(do (conj possible-moves (nth (available-spaces board) spaces-index)) )
      ;)))
;
(defn win [player board depth]
  (if (= player (winner board))
    (- 100 depth)))

(defn loss [opponent board depth]
  (if (= opponent (winner board))
    (do (println "it got here") (+ -100 depth))))

(defn draw [board]
  (if (full? board) 0))

(defn value [board player opponent depth]
  (or (win player board depth)
      (loss opponent board depth)
      (draw board)))
 
(defn minimax-score 
  ([max-player min-player board depth] (minimax-score {:piece max-player :starting-score -5} 
                                                      {:piece min-player :starting-score 5} 
                                                      board (available-spaces board)
                                                      depth -100 100))
  ([player opponent board spaces depth alpha beta]
    (or (value board (:piece player) (:piece opponent) depth)
        (let [new-board (place-move (first spaces) (:piece player) board)]
          (or (value new-board (:piece player) (:piece opponent) (inc depth))
              (minimax-score opponent player new-board (rest spaces) (inc depth) alpha beta))))))
