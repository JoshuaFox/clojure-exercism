(ns nth-prime)
(defn prime? [m]
  (condp #(%1 %2 1) m
    <
    (throw (IllegalArgumentException. (str m)))
    =
    false
    (empty? (filter #(zero? (mod m %)) (range 2 m)))))


(defn nth-prime [n]
  (when (< n 1) (throw (IllegalArgumentException.)))
  (nth (filter prime? (iterate inc 1)) (dec n)))

