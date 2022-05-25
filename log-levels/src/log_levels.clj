(ns log-levels
  (:require [clojure.string :as str]))

(defn message
  "Takes a string representing a log line like
      [ERROR]: Invalid operation
   and returns its message with whitespace trimmed like
      Invalid operation
   "
  [s]
  (let [before-trim (re-find #"(?<=\[[A-Z]+\]\:).*" s)]
    (if before-trim (str/trim before-trim) "")))

(defn log-level
  "Takes a string representing a log line
   and returns its level in lower-case."
  [s]
  (str/lower-case (re-find #"(?<=\[)[A-Z]+(?=]:)" s)))

(defn reformat
  "Takes a string representing a log line and formats it
   with the message first and the log level in parentheses."
  [s]
  (let [lvl (log-level s)
        msg (message s)]
    (str msg " (" lvl ")")))
