(ns acronym
  (:require [clojure.string :as str]))

(defn acronym [sentence] ;; <- arglist goes here
  (let [words1 (str/split sentence #"(?<=[a-z])(?=[A-Z])")
        words2 (mapcat #(str/split % #"\W") words1)
        first-chars (map (comp first str/upper-case) words2)]

    (str/join "" first-chars)))

 