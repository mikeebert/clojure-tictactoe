(ns tictactoe.consoleui)

(defn display-board [board]
  (doseq [row board]
    (println (str " " (first row) " | " (second row) " | " (last row) "\n-----------" ))))

(defn begin []
  (println "Hello and welcome to tic-tac-toe."))

(defn prompt-for-move []
  (println "Please make your next move:"))

(defn parse-int [string]
  (Integer. (re-find #"[0-9]" string)))

(defn get-move [spaces]
  (prompt-for-move)
  (let [input (parse-int (read-line))]
    (if (some #(= input %) spaces)
      input
      (get-move spaces))))

