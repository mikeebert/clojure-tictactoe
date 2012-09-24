(ns tictactoe.game
  (:require [tictactoe.consoleui :refer [begin display-board get-human-move 
                                         winning-message tie-message]]
            [tictactoe.board :refer [available-spaces new-board place-move winner]]))


(defn next-up [player]
  (if (= "X" player)
    "O"
    "X"))

(defn game-loop [board player]
  (let [move (get-human-move player (available-spaces board))
        game-board (place-move move player board)]
    (display-board game-board)
    (if-let [winning-player (winner game-board)]
      (winning-message winning-player)
      (if (empty? (available-spaces game-board))
        (tie-message)
        (game-loop game-board (next-up player))))))

(defn start []
  (begin)
  (display-board new-board)
  (game-loop new-board "X"))
