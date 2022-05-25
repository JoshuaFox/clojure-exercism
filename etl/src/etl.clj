(ns etl
  (:require [clojure.string :as str]))

(defn transform [input]
  "Arg m is the form {1 [a b c] 2 [e d]
  return is in the form {a 1 b 2 c 1 e 2 d 2}"

  (into {} (mapcat (fn [[key lst]]
                     (map #(vector (str/lower-case %) key) lst))
                   input)))
