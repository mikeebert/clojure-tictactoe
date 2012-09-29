(ns tictactoe.ai
  (:require [tictactoe.board :refer [winner full? available-spaces place-move]]))

(defn random-ai-move [board] 
  (rand-nth (available-spaces board)))


(defn abs [n]
  (if (< n 0)
    (- 0 n)
    n))

(defn set-max [piece]
  {:strategy :max :piece piece :starting-score -5 :best-score -5})

(defn set-min [piece]
  {:strategy :min :piece piece :starting-score 5 :best-score 5})

(defn win [player board depth]
  (if (= (:piece player) (winner board))
    (if (= :max (:strategy player))
      (- 100 depth)
      (+ -100 depth))))

(defn loss [opponent board depth]
  (if (= (:piece opponent) (winner board))
    (if (= :max (:strategy opponent))
      (- 100 depth)
      (+ -100 depth))))

(defn draw [board]
  (if (full? board) 0))

(defn value [board player opponent depth]
  (or (win player board depth)
      (loss opponent board depth)
      (draw board)))

;try 1
(defn minimax-score [player opponent board depth]
  (or (value board player opponent depth)
      (if-not (empty? (available-spaces board))
        (let [best-score (:starting-score opponent)
              spaces (available-spaces board)
              new-board (place-move (first spaces) (:piece opponent) board)
              game-value (minimax-score opponent player new-board (inc depth))]
          (if (> (abs game-value) (abs best-score))
            (merge opponent {:best-score game-value}))
          (:best-score opponent)))))

;try 2
;(defn minimax-score [player opponent board depth]
  ;(or (value board player opponent depth)
      ;(doseq [move (available-spaces board)]
        ;(let [best-score (:starting-score opponent)
              ;new-board (place-move move (:piece opponent) board)
              ;game-value (minimax-score opponent player new-board (inc depth))]
          ;(if (> (abs game-value) (abs (:best-score opponent)))
            ;(merge opponent {:best score game-value}))))
      ;(:best-score opponent)))

;(defn minimax-move 
  ;([max min board] (minimax-move max min board 
                                             ;(available-spaces board) {}))
  ;([max-player min-player board spaces move-values]
   ;(if-not (empty? spaces)
     ;(let [depth 0
           ;move (first spaces)
           ;new-board (place-move move (:piece max-player) board)
           ;new-values (merge move-values {move (minimax-score max-player min-player 
                                                              ;new-board (inc depth))})]
      ;(recur max-player min-player board (rest spaces) new-values))
     ;(do (println move-values)
     ;(first (first (sort-by val > move-values)))))))

(defn minimax-move [maxplayer minplayer board]
  (let [move-values (map #(hash-map % (minimax-score maxplayer minplayer 
                                                     (place-move % (:piece maxplayer) board 1))) 
                      (available-spaces board) 1)]
    (println move-values)))
    ;(first (first (sort-by val > move-values)))))

;(declare min-move)

;(defn max-move 
  ;([max-player min-player board] (max-move max-player min-player 
                                           ;board 0 0 (available-spaces board)))
  ;([max-player min-player board best-move depth spaces]
    ;(or (value board max-player min-player depth)
        ;(if-not (empty? spaces)
          ;(let [move (first spaces)
                ;new-board (place-move move (:piece max-player) board)]
          ;(if (value board max-player min-player (inc depth))
            ;(do (if-not (= nil new-value)
              ;(if (> (abs new-value) ((:best-score max-player)))
                ;(do (merge max-player {:best-score new-value})
                  ;(recur max-player min-player board best-move (+ 2 depth) (rest spaces)))
              ;(recur max-player min-player board best-move (+ 2 depth) (rest spaces)))))
            ;(do (let [move (min-move max-player min-player board (+ 2 depth))
                      ;min-board (place-move move (:piece min-player) new-board)]) 
              ;)



            ;)
          ;(best-move)))))

;(defn min-move 
  ;([max-player min-player board depth] (min-move max-player min-player 
                                                ;board 0 depth (available-spaces board)))
  ;([max-player min-player board best-move depth spaces]
   ;(if-not (empty? spaces)
     ;(let [move (max-move max-player min-player board 0 (inc depth) (available-spaces board))
           ;new-board (place-move move (:piece max-player) board)
           ;new-value (value board max-player min-player (inc depth))]
       ;(if-not (= nil new-value)
         ;(if (> (abs new-value) ((:best-score min-player)))
           ;(do (merge min-player {:best-score new-value})
             ;(min-move max-player min-player board move (inc depth) (rest spaces)))
         ;(min-move max-player min-player board best-move (inc depth) (rest spaces)))))
     ;(best-move))))

(defn ai-move [max-piece min-piece board]
  (minimax-move (set-max max-piece) (set-min min-piece) board))
  ;(random-ai-move board))
