(ns anagram
  (:require [clojure.string :as str]))

(defn anagrams-for [word prospect-list]
  (let [sorted-word (str/join "" (sort word))
        sorted-prospects (map #(str/join "" (sort %)) prospect-list)
        pairs (map vector sorted-prospects prospect-list)
        selected-pairs (filter (fn [pair] = sorted-word (first pair)) pairs)
       selected (map second selected-pairs) ]
  (vec selected) ))
