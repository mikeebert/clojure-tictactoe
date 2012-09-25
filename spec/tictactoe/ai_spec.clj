(ns tictactoe.ai-spec
  (:require [speclj.core :refer :all]
            [tictactoe.ai :refer :all]))


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
    (should (some #(= move %) [1 3 4 6 8 9])))))
