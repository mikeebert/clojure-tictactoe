(ns tictactoe.ai
  (:require [tictactoe.board :refer [available-spaces]]))

(defn random-ai-move [board] 
  (rand-nth (available-spaces board)))

(defn ai-move [p1-symbol p2-symbol board]
  (random-ai-move board))

