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
  "Bob is a lackadaisical teenager. In conversation, his responses are very limited.
   Bob answers 'Sure.' if you ask him a question, such as \"How are you?\".
  He answers 'Whoa, chill out!' if you YELL AT HIM (in all capitals).
  He answers 'Calm down, I know what I'm doing!' if you yell a question at him.
  He says 'Fine. Be that way!' if you address him without actually saying anything.
  He answers 'Whatever.' to anything else.
  Bob's conversational partner is a purist when it comes to written communication and always follows normal rules regarding sentence punctuation in English."

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
