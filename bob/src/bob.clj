(ns bob
  (:require [clojure.string :as str])
  (:import (java.lang Character)))
(defn allcaps? [s]
  (let [allcaps (every? (fn [^Character c]
                          (or (not (Character/isLetter ^Character c))
                              (Character/isUpperCase ^Character c)))
                        s)
        somecaps (some #(Character/isUpperCase ^Character %)
                       s)
        ]
    (and somecaps allcaps)))
(defn question? [s]
  (= \? (last s)))

(defn response-for [s]

  (let [trimmed (str/trim s)]
    (cond
      (and (question? trimmed) (allcaps? trimmed))
      "Calm down, I know what I'm doing!"
      (question? trimmed)
      "Sure."
      (empty? (clojure.string/replace s #"\W" ""))
      "Fine. Be that way!"
      (allcaps? trimmed)
      "Whoa, chill out!"
      :else
      "Whatever.")))
