(ns reverse-string
  (:require [clojure.string :as str]))

(defn reverse-vec-recursive [^clojure.lang.PersistentVector v]
  (if (empty? v) [] (conj (reverse-vec-recursive (rest v)) (first v))))

(defn reverse-vec [^clojure.lang.PersistentVector vect]
  (loop [v vect
         acc []]
    (if (empty? v)
      acc
      (recur (butlast v) (conj acc (last v))))))

(defn reverse-string [^String s]
  (str/join "" (reverse-vec (vec s))))