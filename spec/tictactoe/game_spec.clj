(ns tictactoe.game-spec
  (:require [speclj.core :refer :all]
            [tictactoe.game :refer :all]
            [tictactoe.consoleui :as console]))

(describe "Game"
  
  (it "should fire off the greeting and display a new board"
    (with-redefs [console/begin (fn [x] x)
                  console/display-board (fn [x] x)]
    (should= [[1 2 3]
              [4 5 6]
              [7 8 9]] (start))))
  
  (it "should send the baord to the console"
    (with-redefs [console/display-board (fn [x] x)]
      (should= true (start)))))
