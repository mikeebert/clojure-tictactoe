(ns tictactoe.board-spec
  (:require [speclj.core :refer :all]
            [tictactoe.board :refer :all]))

(def fresh-board [[1 2 3]
                  [4 5 6]
                  [7 8 9]])

(describe "Board"

  (it "should have new board"
    (should= fresh-board new-board))

  (it "should flatten an array of arrays"
    (should= [1 2 3 4 5 6 7 8 9] (flatten fresh-board)))

  (it "should return the avaialable spaces on a board"
    (should= [1 3 5 7 8] (available-spaces [[1 "x" 3]
                                            ["x" 5 "x"]
                                            [7 8 "x"]] )))

  (it "should allow a move to be made on the board"
    (should= [[1 "x" 3]
              [4 5 6]
              [7 8 9]] (place-move 2 "x" new-board)))

  (it "should return a diagonal winner"
    (should= "x" (diagonal-winner [["x" 1 2]
                                    [3 "x" 5]
                                    [6 7 "x"]]))
    (should= "x" (diagonal-winner [[0 1 "x"]
                                    [3 "x" 5]
                                    ["x" 7 8]])))

  (it "#winner should return a horizontal winner from a 3 x 3 array"
    (should= "x" (winner [["x", "x", "x"]
                          [3 4 5]
                          [6 7 8]]))
    (should= "x" (winner [[0 1 2]
                          ["x", "x", "x"]
                          [6 7 8]]))
    (should= "x" (winner [[0 1 2]
                          [3 4 5]
                          ["x", "x", "x"]])))

  (it "#winner should return a vertical winner "
    (should= "x" (winner [["x" 1 2]
                          ["x" 4 5]
                          ["x" 7 8]])))

  (it "should return a diagonal winner"
    (should= "x" (winner [["x" 1 2]
                          [3 "x" 5]
                          [6 7 "x"]]))

    (should= "x" (winner [[0 1 "x"]
                          [3 "x" 5]
                          ["x" 7 8]])))

  (it "#winner should return nil for an empty game"
    (should= nil (winner [[0 1 2]
                          [3 4 5]
                          [6 7 8]])))

  (it "#winner should return nil for a cat's game"
    (should= nil (winner [["x" "o" "x"]
                          ["o" "o" "x"]
                          ["x" "x" "o"]]))))


