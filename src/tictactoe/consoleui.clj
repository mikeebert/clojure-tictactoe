(ns tictactoe.consoleui)

(defn display-board [board]
  (doseq [row board]
    (println (str " " (first row) " | " (second row) " | " (last row) "\n-----------" ))))

(defn greeting []
  (println "Hello and welcome to tic-tac-toe."))

(defn prompt-for-move [player]
  (println (str player " player, please make your next move:")))

(defn parse-int [string]
  (Integer. (re-find #"[0-9]" string)))

(defn get-human-move [player spaces]
  (prompt-for-move player)
  (try 
    (let [input (parse-int (read-line))]
      (if (some #(= input %) spaces)
        input
        (do (println "Not a valid move.") (get-human-move player spaces))))
    (catch Exception e (get-human-move player spaces))))

(defn get-player-type [player-symbol]
  (println (str "Would you like player " player-symbol " to be a human or computer?"))
  (println "1. Human\n2. Computer")
  (try (parse-int (read-line))
    (catch Exception e (get-player-type player-symbol))))

(defn winning-message [player]
  (println (str player " wins!")))

(defn tie-message []
  (println "Cat's game."))
