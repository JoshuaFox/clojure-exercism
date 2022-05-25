(ns cars-assemble)

(defn success-rate
  [speed]
  (cond
    (= speed 0) 0.0
    (>= 4 speed 1) 1.0
    (>= 8 speed 5) 0.9
    (= speed 9) 0.8
    (= speed 10) 0.77
    :else (throw (Exception. (str "Unsupported " speed)))))


(defn production-rate
  "Returns the assembly line's production rate per hour,
   taking into account its success rate"
  [speed]
  (-> speed
      (#(* % 221))
      (#(* % (success-rate speed)))))

(defn working-items
  "Calculates how many working cars are produced per minute"
  [speed]

  (int (/ (production-rate speed) 60)))
