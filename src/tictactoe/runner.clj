(ns tictactoe.runner
  (:require [tictactoe.game :refer [start]])
  (:gen-class :main true))

(defn -main []
  (start))
