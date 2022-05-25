(ns pig-latin
  (:require [clojure.string :as str]))
;Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of the
; word. Please note that "xr" and "yt" at the beginning
; of a word make vowel sounds (e.g. "xray" -> "xrayay", "yttria" -> "yttriaay").
;Rule 2: If a word begins with a consonant sound, move
; it to the end of the word and then add an "ay" sound to
; the end of the word. Consonant sounds can be made up of multiple
; consonants, a.k.a. a consonant cluster (e.g. "chair" -> "airchay").
;Rule 3: If a word starts with a consonant sound followed by "qu",
; move it to the end of the word, and then add an "ay" sound
; to the end of the word (e.g. "square" -> "aresquay").
;Rule 4: If a word contains a "y" after a consonant cluster
; or as the second letter in a two letter word it makes a
; vowel sound (e.g. "rhythm" -> "ythmrhay", "my" -> "ymay").


(defn consonant-prefix [word]
  (let [pfx (take-while #(str/includes? "bcdfghjklmnpqrstvwxy" (str %)) word)
        pfx-count (count pfx)]
    (if (= "qu" (subs word (dec pfx-count) (inc pfx-count)))
      (subs word 0 (inc pfx-count))                         ;include the u
      pfx)))


(defn pig [w]
  (cond
    (some #(str/starts-with? w %) ["a" "e" "i" "o" "u" "yt" "xr"])
    (str/join "" [w "ay"])
    (consonant-prefix w)                                    ; how do I assign this to a vbl just for this part of the cond?
    (str/join "" (concat (nthrest w (count (consonant-prefix w))) (consonant-prefix w) "ay"))
    :else
    (assert false "Should never reach here, as cons-pfx always return a string, even if empty")))

(defn translate [phrase]
  (let [words (str/split phrase #"\W")]
    (str/join " " (for [w words] (pig w)))))

