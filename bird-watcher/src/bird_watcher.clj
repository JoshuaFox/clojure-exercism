(ns bird-watcher)

(def last-week
  [0 2 5 3 7 8 4])
(def birds-per-day [2 5 0 7 4 1])

(defn today [birds]
  (last birds))

(defn inc-bird [birds]
  (conj (vec (butlast birds)) (inc (today birds))))

(defn day-without-birds? [birds]
  (boolean (some #(= 0 %) birds)))

(defn n-days-count [birds n]
  (reduce + (take n birds)))

(defn busy-days [birds]
  (count (filter #(>= % 5) birds)))

(defn odd-week? [birds]
  (= birds [1 0 1 0 1 0 1]))
