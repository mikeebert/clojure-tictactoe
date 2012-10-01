(ns tictactoe.console-runner
  (:require [tictactoe.consoleui :refer [greeting display-board
                                         get-player-type get-human-move
                                         winning-message tie-message]]
            [tictactoe.board :refer [new-board available-spaces place-move winner full?]]
            [tictactoe.ai :refer [ai-move]]))

(defn get-player [player-symbol] 
  (let [input (get-player-type player-symbol)]
    (if (= 1 input)
      {:symbol player-symbol, :type :human}
      {:symbol player-symbol, :type :computer})))

(defn get-ai-move [p1-symbol p2-symbol board]
  (ai-move p1-symbol p2-symbol board))

(defn next-player-move [player next-player board]
  (if (= :human (:type player))
    (get-human-move (:symbol player) (available-spaces board))
    (get-ai-move (:symbol player) (:symbol next-player) board)))

(defn end-of-game [board]
 (if-let [winning-player (winner board)]
  (winning-message winning-player)
  (if (full? board)
    (tie-message))))

(defn game-loop [player next-player board]
    (let [move (next-player-move player next-player board)
          game-board (place-move move (:symbol player) board)]
      (display-board game-board)
      (or (end-of-game game-board)
          (game-loop next-player player game-board))))

(defn start []
  (greeting)
  (let [player1 (get-player "X")
        player2 (get-player "O")]
  (display-board new-board)
  (game-loop player1 player2 new-board)))

