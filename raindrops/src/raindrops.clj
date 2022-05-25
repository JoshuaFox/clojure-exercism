(ns raindrops)

(defn convert [n]
  "   The rules of raindrops are that if a given number:
      has 3 as a factor, add 'Pling' to the result.
      has 5 as a factor, add 'Plang' to the result.
      has 7 as a factor, add 'Plong' to the result.
      does not have any of 3, 5, or 7 as a factor, the result should be the digits of the number.
  "
  (let [factor-based (for [[k v]
                           {3 "Pling", 5 "Plang" 7 "Plong"}
                           :when (zero? (mod n k))]
                       v)]
    (if (empty? factor-based) (str n) (apply str factor-based))))

