(ns rna-transcription)

(defn to-rna [dna] {:post [(= (count %) (count dna))]}
  (apply str (for [base dna] ({\G \C, \C \G, \T \A, \A \U} base))))


