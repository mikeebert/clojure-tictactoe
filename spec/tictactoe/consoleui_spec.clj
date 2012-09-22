(ns tictactoe.consoleui-spec
  (:require [speclj.core :refer :all]
            [tictactoe.consoleui :refer :all]))

(def board [[1 2 3]
            [4 5 6]
            [7 8 9]])

(describe "Console"

  (it "should convert a string entry into an integer"
    (should= (parse-int "5") 5))

  (it "shoud reject a non integer")

  (it "should reject an ineligible move")
    ;(with-redefs [read-line (1 "1")
                  ;read-line (1 "9")]
      ;(should= (get-move [1 9]) 9)))

  (it "should get a move"
    (with-redefs [read-line (constantly "1")]
      (should= 1 (get-move [1 2 3]))))
  
  (it "should display the board"
    (with-redefs [println (constantly true)]
    (should= true (display-board board)))))
