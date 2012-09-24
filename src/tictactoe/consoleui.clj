(ns tictactoe.consoleui)

(defn display-board [board]
  (doseq [row board]
    (println (str " " (first row) " | " (second row) " | " (last row) "\n-----------" ))))

(defn begin []
  (println "Hello and welcome to tic-tac-toe."))

(defn prompt-for-move [player]
  (println (str player " player, please make your next move:")))

(defn parse-int [string]
  (Integer. (re-find #"[0-9]" string)))

(defn get-move [player spaces]
  (prompt-for-move player)
  (try 
    (let [input (parse-int (read-line))]
      (if (some #(= input %) spaces)
        input
        (do (println "Not a valid move.") (get-move player spaces))))
    (catch Exception e (get-move player spaces))))

(defn winning-message [player]
  (println (str player " wins!")))

(defn tie-message []
  (println "Cat's game."))
