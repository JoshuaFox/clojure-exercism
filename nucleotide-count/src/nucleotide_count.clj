(ns nucleotide-count)

(defn nucleotide-counts [strand]
  {
   :post [(= (reduce + (vals %)) (count strand))]}
  (let [ret (into {\A 0, \T 0, \C 0, \G 0} (for [nuc [\A \T \C \G]]
                                             [nuc (count (filter #(= % nuc) strand))]))]
    (when (< (reduce + (vals ret)) (count strand)) (throw (IllegalArgumentException. strand)))
    ret))

(defn count-of-nucleotide-in-strand [nucleotide strand]
  {:pre [(#{\A \T \C \G} nucleotide)]}
  (nucleotide-counts strand)                                ;just for assertion
  (count (filter #(= % nucleotide) strand)) )