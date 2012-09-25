(ns tictactoe.runner
  (:require [tictactoe.console-runner :refer [start]])
  (:gen-class :main true))

(defn -main []
  (start))
