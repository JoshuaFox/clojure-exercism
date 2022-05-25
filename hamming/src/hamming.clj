(ns hamming)

(defn distance [strand1 strand2]
  "Count the differences between them.
  An attempt to calculate it between sequences of different lengths should not work."
  (when (= (count strand1) (count strand2))
    (count (filter false? (map = strand1 strand2)))))
