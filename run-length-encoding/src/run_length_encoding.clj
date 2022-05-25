(ns run-length-encoding)
(defn- encode-run [pfx] {:pre [(= 1 (count (set pfx)))]}
  (str (if (= (count pfx) 1) "" (count pfx))
       (first pfx)))

(defn run-length-encode
  "encodes a string with run-length-encoding, "
  [plain-text] {:pre [(nil? (re-find #"[^A-Za-z ]" plain-text))]}
  (let [pfx-groups (re-find #"^(.)\1*" plain-text)]
    (if (nil? pfx-groups)
      ""
      (let [pfx (first pfx-groups)]
        (str (encode-run pfx) (run-length-encode (subs plain-text (count pfx))))))))

(defn- third [coll] (nth coll 2))

(defn parse-int [num-pfx]
  (if (empty? num-pfx)
    1
    (Integer/parseInt num-pfx)))

(defn run-length-decode
  "decodes a run-length-encoded string"
  [cipher-text]
  {:pre [(nil? (re-find #"[^A-Za-z0-9 ]" cipher-text))]}
  (let [pfx-groups (re-find #"(\d*)([A-Za-z ])" cipher-text)]
    (if (nil? pfx-groups)
      ""
      (let [pfx (first pfx-groups)
            num-pfx (second pfx-groups)
            str-pfx (third pfx-groups)]
        (apply str (concat (repeat (parse-int num-pfx) str-pfx)
                           (run-length-decode (subs cipher-text (count pfx)))))))))
