(ns accumulate)

(defn accumulate [f coll]
  (loop [v coll
         acc []]
    (if (empty? v)
      acc
      (recur (rest v) (conj acc (f (first v)))))))
