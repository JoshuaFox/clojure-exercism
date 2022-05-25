(ns pig-latin
  (:require [clojure.string :as str]))


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

