(ns all-your-base)

(defn- parse-recursive [s base]
  (if (empty? s)
    0
    (+ (first s) (* base (parse-recursive (rest s) base)))))

(defn parse [s base]
  (parse-recursive (reverse s) base))

(defn render
  ([n base]
   (render n base '()))
  ([n base accum]
   (if (zero? n)
     (if (empty? accum) '(0) accum)
     (let [digit (mod n base)
           appended (conj accum digit)]
       (render (quot n base) base appended)))))



(defn- invalid [base1 s base2]
  (let [invalid-digit (fn [s base1]
                        (not (every? (fn [digit] (< -1 digit base1)) s)))]
    (or (>= 1 base1) (>= 1 base2)
        (neg? base1) (neg? base2)
        (invalid-digit s base1))))

(defn convert [base1 s base2]                               ;; <- arglist goes here
  (cond (invalid base1 s base2)
        nil
        (empty? s)
        '()
        :else
        (render (parse s base1) base2)))


