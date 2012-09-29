(ns tictactoe.ai-spec
  (:require [speclj.core :refer :all]
            [tictactoe.ai :refer :all]))

(def one-move-win [["x" "x" 3]
                   ["x" "o" "o"]
                   ["o" "x" "o"]])

(def won-board [["x" "x" "x"]
                ["x" "o" "o"]
                ["o" "x" "o"]])

(def draw-board [["x" "o" "x"]
                ["o" "x" "x"]
                ["o" "x" "o"]])

(def two-move-o-win [["x" 2 3]
                     ["x" 5 6]
                     ["o" "x" "o"]] )

(def max-x-player {:strategy :max :piece "x" :starting-score -5})
(def min-o-player {:stategy :min :piece "o" :starting-score 5})

(describe "AI"
  
  (it "should temporarily return a random move"
    (let [move (ai-move "x" "o" [[1 "x" 3]
                                       [4 "o" 6]
                                       ["x" 8 9]])]
    (should (some #(= move %) [1 3 4 6 8 9]))))

  (it "should return a random move"
    (let [move (random-ai-move [[1 "x" 3]
                                [4 "o" 6]
                                ["x" 8 9]])]
    (should (some #(= move %) [1 3 4 6 8 9]))))

  (it "should score a won board for a player"
    (should= 99 (value won-board max-x-player min-o-player 1)))

  (it "should score a won board for an opponent"
    (should= -99 (value won-board {:strategy :max :piece "o" :starting-score -5}
                                  {:strategy :min :piece "x" :starting-score 5} 1)))

  (it "should score a draw board"
    (should= 0 (value draw-board min-o-player max-x-player 1)))

  (it "should not score a game in progress"
    (should= nil (value one-move-win min-o-player max-x-player 6)))


  (it "should return a won board with a score"
    (should= 93 (minimax-score max-x-player min-o-player won-board 7)))

  (it "should return a cat's game board with a score"
    (should= 0 (minimax-score max-x-player min-o-player draw-board 7)))

  (it "should score for a loss in two moves"
    (should= -98 (minimax-score max-x-player min-o-player one-move-win 1)))

  (it "should block a 'knight' set-up move"
    (let [move (ai-move max-x-player min-o-player [["o" 2 3]
                                                   [4 "x" 6]
                                                   [7 "o" 9]])]
    (should (some #(= move %) [4 7 6 9]))))

  (it "should provide for a win in one move"
    (should= 4 (ai-move max-x-player min-o-player [["x" 2 "o"]
                                                   [4 "o" "x"]
                                                   ["x" "o" "x"]]))))
