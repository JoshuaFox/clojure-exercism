(ns protein-translation
  (:require [clojure.string :as str]))




(defn line-to-map [line]
  (->
    line
    str/trim
    (#(-> %
          (str/replace #",\s+" ",")
          (str/split #"\s+")))
    ((fn split-codes [keys-val] [(str/split (first keys-val) #",") (second keys-val)]))
    ((fn to-map [keys-val]
       (into {} (map #(vector % (second keys-val)) (first keys-val)))))))
(def table-s
  "AUG 	Methionine
  UUU, UUC 	Phenylalanine
   UUA, UUG 	Leucine
   UCU, UCC, UCA, UCG 	Serine
   UAU, UAC 	Tyrosine
   UGU, UGC 	Cysteine
   UGG 	Tryptophan
   UAA, UAG, UGA 	STOP")
(defn- lookup-table-from-str [table-str] (->>
                                 table-str
                                 (str/split-lines)
                                 (map line-to-map)
                                 (apply merge)))
(def lookup-table (lookup-table-from-str table-s))

(defn translate-codon [code] {:post (some? %)}
  (lookup-table code))

(defn translate-rna [rna]
  (->> rna
       (partition 3)
       (map #(str/join "" %))
       (take-while #(not= "STOP" (translate-codon %)))
       (map translate-codon)))


(defn square [x] (* x x))
(defn twice [x] (+ x x))
(apply (comp square twice inc) [2])
