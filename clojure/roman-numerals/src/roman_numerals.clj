(ns roman-numerals)

(def units
  {0 "I" 1 "X" 2 "C" 3 "M"})

(def fives
  {0 "V" 1 "L" 2 "D"})

(defn- negative-base [digit magnitude]
  (case digit
    4
    (str (units magnitude) (fives magnitude))
    9
    (str (units magnitude) (units (inc magnitude)))
    (throw (IllegalArgumentException. (str digit)))))

(defn- repeat-units [quantity magnitude]
  (take quantity (repeat (units magnitude))))

(defn- roman-digit [digit magnitude]

  (apply str (case digit
               0
               ""
               5
               (fives magnitude)
               (4 9)
               (negative-base digit magnitude)
               (1 2 3)
               (repeat-units digit magnitude)
               (6 7 8)
               (conj (repeat-units (- digit 5) magnitude) (fives magnitude)))))

(defn digits- [n]
  (if (< n 10)
    [n]
    (conj (digits- (quot n 10)) (mod n 10))))

(defn numerals [n] {:pre [(< 0 n 3900)]}
  (let [zipped (map vector (digits- n) (reverse (range (count (digits- n)))))]
    (apply str (map (fn [[digit magnitude]] (roman-digit digit magnitude)) zipped))))
