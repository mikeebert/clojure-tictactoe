(ns tictactoe.board)

(def new-board [[1 2 3]
                [4 5 6]
                [7 8 9]])

(defn position
  ([game-board move] (position game-board move 0))
  ([game-board move index]
      (if (= (nth game-board index) move)
        index
        (recur game-board move (inc index)))))

(defn available-spaces [board]
  (filter (fn [n] (= java.lang.Long (class n))) (flatten board)))

(defn place-move [move piece game-board]
  (map (fn [row]
         (if (some #(= move %) row)
           (assoc row (position row move) piece)
           row))game-board))

(defn full? [board]
  (not-any? integer? (flatten board)))

(defn transpose [board]
  (apply map vector board))

(defn row-or-column-winner [board]
  (first (first (filter (fn [row-or-column] (= 1 (count (set row-or-column)))) board))))

(defn diagonal-winner [board]
  (if (= (first (nth board 0)) (second (nth board 1)) (last (nth board 2)))
    (first (nth board 0))
    (if (= (last (nth board 0)) (second (nth board 1)) (first (nth board 2)))
      (last (nth board 0))
      nil)))

(defn winner [board]
  (or (row-or-column-winner board)
      (row-or-column-winner (transpose board))
      (diagonal-winner board)))
