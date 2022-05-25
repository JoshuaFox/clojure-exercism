(ns rotational-cipher)

(def alphabet-len 26)

(defn a-as-int [lower-case?]
  (int (if lower-case? \a \A)))

(defn cycle-alphabet [lower-case?]
  (cycle (map #(char (+ % (a-as-int lower-case?)))
              (range alphabet-len))))

(defn rotate-char [^Character ch rotation]
  (let [lower-case? (Character/isLowerCase ch)]
    (if
      (Character/isLetter ch)
      (nth (cycle-alphabet lower-case?)
           (mod (+ rotation (- (int ch) (a-as-int lower-case?))) alphabet-len))
      ch)))

(defn rotate [text rotation]
  (apply str (map #(rotate-char % rotation) text)))