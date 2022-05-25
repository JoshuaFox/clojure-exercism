(ns secret-handshake)

(def ^:private command-vec
  ["wink" "double blink" "close your eyes" "jump"
   :reverse])

(defn base [n to])

(defn enabled-bottom5-bits [num])

(defn commands [^Integer n]
  (let [enabled (map #(command-vec %) (enabled-bottom5-bits n))
        has-reverse? (some #(= :reverse %) enabled)
        without-reverse (filter #(not= :reverse %) enabled)
        ordering-func (if has-reverse? reverse identity)
        in-order (ordering-func without-reverse)]
    in-order))

(defn enabled-bottom5-bits [num]
  (let [n (mod num 32)
        binary-lower-order-first (reverse (base n 2))
        filtered (filter #(= 1 (first %)) (map vector binary-lower-order-first (range (count binary-lower-order-first))))]
    (map second filtered)))

(defn base [n to]
  (if (< n to)
    [n]
    (conj (base (quot n to) to) (mod n to))))


