(ns interest-is-interesting
  (:import (java.lang Math)))

(defn interest-rate
  "-3.213% for a negative balance.
  0.5% for a positive balance less than 1000 dollars.
  1.621% for a positive balance greater or equal than 1000 dollars and less than 5000 dollars.
  2.475% for a positive balance greater or equal than 5000 dollars"
  [balance]
  (cond
    (< balance 0) -3.213
    (= balance (bigdec 0)) 0.5                              ;not in spec
    (< 0 balance 1000) 0.5
    (<= 5000 balance) 2.475
    (<= 1000 balance) 1.621
    :else (throw (Exception. (str "Unsupported " balance)))))

(defn annual-balance-update
  [balance]
  (let [interest-absolute (Math/abs (double (interest-rate balance)))
        multiplier (bigdec (+ 1.0 (/ interest-absolute 100.0)))]
    (* (bigdec balance) multiplier)))

(defn amount-to-donate
  [balance tax-free-percentage]
  (if (> balance 0)
    (bigint (/ (Math/round (double (* 2 tax-free-percentage balance))) 100.0))
    0))