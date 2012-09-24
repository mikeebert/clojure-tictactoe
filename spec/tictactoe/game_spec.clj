(ns tictactoe.game-spec
  (:require [speclj.core :refer :all]
            [tictactoe.game :refer :all]
            [tictactoe.board :refer [winner place-move]]
            [tictactoe.consoleui :as console]
            [tictactoe.consoleui :refer [display-board get-human-move]]))

(def mock-board [[][][]])

(describe "Game"
  
  ;(it "should tell the UI to begin"
    ;(with-redefs [console/begin (constantly true)]
    ;(should= true (start))))
  
  (it "should send the board to the console"
    (with-redefs [console/display-board (fn [x] x)]
      (should= [1 2 3] (display-board [1 2 3]))))
  
  (it "should start a game loop that gets input and checks for a winner"
    (with-redefs [get-human-move (fn [player spaces] nil)
                  println (constantly true)]
      (should= true (game-loop mock-board "x"))))
  
  (it "can return the next player"
    (should= "X" (next-up "O")))
  
  (it "can return the next o player"
    (should= "O" (next-up "X"))))
