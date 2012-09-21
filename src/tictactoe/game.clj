(ns tictactoe.game
  (:require [tictactoe.consoleui :refer [begin]]
            [tictactoe.board :refer [new-board]]))

(defn start []
  (begin new-board)
  )
