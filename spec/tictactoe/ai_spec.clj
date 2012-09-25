(ns tictactoe.ai-spec
  (:require [speclj.core :refer :all]
            [tictactoe.ai :refer :all]))

(def one-move-x-win [["x" "x" 3]
                   ["x" "o" "o"]
                   ["o" "x" "o"]])

(def won-board [["x" "x" "x"]
                ["x" "o" "o"]
                ["o" "x" "o"]])

(def draw-board [["x" "o" "x"]
                ["o" "x" "x"]
                ["o" "x" "o"]])

(def two-move-o-win [["x" "o" 3]
                     ["o" 5 "o"]
                     ["o" "x" "o"]] )

;(def max-player {:symbol "x" :starting-score -5})
;(def min-player {:symbol "o" :starting-score 5})

(describe "AI"
  
  (it "should temporarily return a random move"
    (let [move (ai-move "x" "x" [[1 "x" 3]
                                       [4 "o" 6]
                                       ["x" 8 9]])]
    (should (some #(= move %) [1 3 4 6 8 9]))))

  (it "should return a random move"
    (let [move (random-ai-move [[1 "x" 3]
                                [4 "o" 6]
                                ["x" 8 9]])]
    (should (some #(= move %) [1 3 4 6 8 9]))))
  
  (it "should score a won board for a player"
    (should= 99 (value won-board "x" "o" 1)))

  (it "should score a won board for an opponent"
    (should= -99 (value won-board "o" "x" 1)))

  (it "should score a draw board"
    (should= 0 (value draw-board "o" "x" 1)))

  (it "should not score a game in progress"
    (should= nil (value one-move-x-win "o" "x" 6)))

  (it "should return a won board with a score"
    (should= 93 (minimax-score "x" "o" won-board 7)))

  (it "should return a cat's game board with a score"
    (should= 0 (minimax-score "x" "o" draw-board 7)))

  (it "should score for a win in one move"
    (should= 99 (minimax-score "x" "o" one-move-x-win 0)))
  
  (it "should score for a loss in two moves"
    (should= -98 (minimax-score "x" "o" two-move-o-win 0))))
