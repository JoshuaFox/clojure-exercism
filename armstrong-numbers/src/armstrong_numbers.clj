(ns armstrong-numbers)

(defn digits [n]
  (loop [decreasing n
         acc '()]
    (if (zero? decreasing)
      acc
      (recur (quot decreasing 10) (conj acc (mod decreasing 10))) )))

(defn count-digit [n]
  (if (zero? (quot n 10)) 1 (+ 1 (count-digit (quot n 10)))))

(defn exp [x n]
  (reduce *  (repeat n x)))

(defn armstrong? [num]
  "An Armstrong number is a number that is the sum of its own digits each raised to the power of the number of digits."
  (let [len (count-digit num)]
    (= num (reduce +  (map #(exp % len) (digits num)))))
  )

