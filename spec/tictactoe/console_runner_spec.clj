(ns tictactoe.console-runner-spec
  (:require [speclj.core :refer :all]
            [tictactoe.console-runner :refer :all]
            [tictactoe.consoleui :refer [get-player-type get-human-move 
                                         winning-message tie-message]]
            [tictactoe.board :refer [available-spaces]]
            [tictactoe.ai :refer [ai-move]]))

(def computer-player-O {:symbol "O" :type :computer})
(def human-player-X {:symbol "X" :type :human})
(def fresh-board [[1 2 3]
                  [4 5 6]
                  [7 8 9]])

(def tie-board [["x" "x" "o"]
                ["o" "o" "x"]
                ["x" "o" "x"]])

(defn make-input [coll]
  (apply str (interleave coll (repeat "\n"))))

(describe "Console Runner"
 
  (it "should ask the console for the player type and return a human player"
    (with-redefs [println (constantly nil)
                  get-player-type (constantly 1)]
      (should= human-player-X (get-player "X"))))

  (it "should ask the console for the player type and return a computer player"
    (with-redefs [println (constantly nil)
                  get-player-type (constantly 2)]
      (should= computer-player-O (get-player "O"))))

  (it "should get the player details from the ui and fire it off to the game loop"
    (with-redefs [println (constantly nil)
                  game-loop (fn [x y z] [x y z])]
      (with-in-str (make-input '(1 2))
        (should= [human-player-X computer-player-O fresh-board] 
               (start)))))
  
  (it "should get the move if the next player is a human"
    (with-redefs [println (constantly nil)
                  available-spaces (constantly [1 2 3]) 
                  get-human-move (fn [sym spaces] [sym spaces])]
      (let [player1 human-player-X
            player2 computer-player-O
            board ["some_board"]]
        (should= ["X" [1 2 3]] (next-player-move player1 player2 board)))))
  
   (it "should get the move if the next player is a computer"
    (with-redefs [println (constantly nil)
                  get-ai-move (fn [x y z] [x y z])]
      (let [player2 human-player-X
            player1 computer-player-O
            board :board]
        (should= ["O" "X" :board] (next-player-move player1 player2 board)))))
  
  (it "should ask the AI for a move"
    (with-redefs [ai-move (fn [x y z] [x y z])]
      (let [p1 "x"
            p2 "o"
            board :board]
      (should= ["x" "o" :board] (get-ai-move p1 p2 board)))))

  (it "should return true for game-over if there is a winner"
    (should= true (game-over [["x" "x" "x"]
                              [4 5 6]
                              [7 8 9]])))

  (it "should return true for game-over if there is a tie"
    (should= true (game-over tie-board)))

  (it "should return nil for a game in progress"
    (should= false (game-over fresh-board)))

  (it "should start a game loop"
    (let [player1 human-player-X
          player2 {:symbol "O" :type :human}
          board [[1 2 3][4 5 6][7 8 9]]]
      (with-in-str (make-input '(1 2 3 4 5 6 7))
        (with-redefs [println (constantly nil)
                      winning-message (fn [x] x)
                      shutdown-agents (constantly :ran-loop)]
    (should= :ran-loop (game-loop player1 player2 board)))))))
  
  
