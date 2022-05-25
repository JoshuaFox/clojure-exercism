(ns pangram
  (:require [clojure.string :as str]))

(defn pangram? [s]
  (let [a-to-z (set (map #(char (+ % (int \a))) (range 0 26)))
        letters-in-input (set (str/lower-case s))]
    (empty? (filter #(nil? (letters-in-input %)) a-to-z))))
