(ns tictactoe.board)

(def new-board (vec (range 1 10)))

(defn position
  ([game-board move] (position game-board move 0))
  ([game-board move index]
      (if (= (nth game-board index) move)
        index
        (recur game-board move (inc index)))))

(defn place-move [move piece game-board]
  (assoc game-board (position game-board move) piece))

(defn transpose [board]
  (apply map vector board))

(defn row-or-column-winner [board]
  (if-not (empty? (filter (fn [row-or-column] (= 1 (count (set row-or-column)))) board))
    (first (first (filter (fn [row-or-column] (= 1 (count (set row-or-column)))) board)))))

(defn diagonal-winner [board]
  (if (= (first (nth board 0)) (second (nth board 1)) (last (nth board 2)))
    (first (nth board 0))
    (if (= (last (nth board 0)) (second (nth board 1)) (first (nth board 2)))
      (last (nth board 0))
      nil)))

(defn winner [board]
  (if-let [winner (row-or-column-winner board)]
    winner
    (if-let [winner (row-or-column-winner (transpose board))]
      winner
      (if-let [winner (diagonal-winner board)]
        winner
        nil))))
