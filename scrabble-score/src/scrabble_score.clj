(ns scrabble-score
  (:require [clojure.string :as str]))

; Lookup table code copied from protein-translation
(def table-s
  "A, E, I, O, U, L, N, R, S, T       1
D, G                               2
B, C, M, P                         3
F, H, V, W, Y                      4
K                                  5
J, X                               8
Q, Z                               10")

(defn- line-to-map [line]
  (->
    line
    str/trim
    (#(-> %
          (str/replace #",\s+" ",")
          (str/split #"\s+")))
    ((fn split-codes [keys-val] [(str/split (first keys-val) #",") (second keys-val)]))
    ((fn to-map [keys-val]
       (into {} (map #(vector % (Integer/parseInt (second keys-val))) (first keys-val)))))))

(defn- lookup-table-from-str [table-str] (->>
                                           table-str
                                           (str/split-lines)
                                           (map line-to-map)
                                           (apply merge)))
(def ^:private lookup-table (lookup-table-from-str table-s))


(defn score-letter [letter]
  (lookup-table (str/upper-case letter)))

(defn score-word [word]
  (reduce + (map score-letter word)))

