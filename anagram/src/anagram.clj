(ns anagram
  (:require [clojure.string :as str]))

(defn anagrams-for [word prospect-list]
  (let [sorted-word (str/join "" (sort (map str/lower-case word)))
        sorted-prospects (map #(str/join "" (sort (map str/lower-case %))) prospect-list)
        pairs (map vector sorted-prospects prospect-list)
        anagram-pairs (filter (fn [pair] (= sorted-word (first pair))) pairs)
        anagrams (map second anagram-pairs)
        not-self (filter #(not= (str/lower-case word) (str/lower-case %)) anagrams)  ]
    (vec not-self )))
