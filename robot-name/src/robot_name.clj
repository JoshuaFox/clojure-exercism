(ns robot-name)
(defrecord Robot [name])


(defn random-char []
  (let [alphabet "ABCDEFGHIJKLMNOPQRSTUVWZYZ"
        rand-idx (rand-int (count alphabet))]
    (nth alphabet rand-idx)))

(defn random-name [] (str (random-char) (random-char)
                          (rand-int 10) (rand-int 10) (rand-int 10)))

(defn robot []
  (atom (->Robot (random-name))))

(defn robot-name [robot]
  (:name @robot))

(defn reset-name [robot]
  (swap! robot #(assoc % :name (random-name))))
