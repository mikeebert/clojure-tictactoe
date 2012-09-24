(ns tictactoe.consoleui-spec
  (:require [speclj.core :refer :all]
            [tictactoe.consoleui :refer :all]))

(def board [[1 2 3]
            [4 5 6]
            [7 8 9]])

(def mock-player "x")

(defn make-input [coll]
  (apply str (interleave coll (repeat "\n"))))

(describe "Console"

  (it "should prompt a player for a move"
    (with-redefs [prompt-for-move (fn [x] x)]
      (should= "x" (prompt-for-move "x"))))

  (it "should convert a string entry into an integer"
    (should= (parse-int "5") 5))

  (it "shoud reject a non integer and ask for another move"
    (with-redefs [println (constantly nil)]
      (with-in-str (make-input '("nine" 3))
        (should= 3 (get-move mock-player [1 2 3])))))

  (it "should accept a valid move"
    (with-redefs [println (constantly nil)
                  read-line (constantly "9")]
      (should= 9 (get-move "x" [1 9]))))

  (it "should reject an invalid move"
    (with-redefs [println (constantly nil)]
      (with-in-str (make-input '(2 9))
        (should= 9 (get-move "x" [1 9])))))

  (it "should get a move"
    (with-redefs [println (constantly nil)
                  read-line (constantly "1")]
      (should= 1 (get-move "x" [1 2 3]))))
  
  (it "should display the board"
    (with-redefs [println (constantly true)]
    (should= true (display-board board)))))
