(ns complex-numbers)

"
Raising e to a complex exponent can be expressed as e^(a + i * b) = e^a * e^(i * b),
 the last term of which is given by Euler's formula e^(i * b) = cos(b) + i * sin(b).

Implement the following operations:
    addition, subtraction, multiplication and division of two complex numbers,
    conjugate, absolute value, exponent of a given complex number.
Assume the programming language you are using does not have an implementation of complex numbers."

(defn real [[a b]]
  a)

(defn imaginary [[a b]]
  b)

(defn abs [[a b]]
  (Math/sqrt (+ (* a a) (* b b))))

(defn conjugate [[a b]]
  [a (- b)])

(defn add [[a b] [c d]]
  [(+ a c) (+ b d)])

(defn sub [[a b] [c d]]
  [(- a c) (- b d)])

(defn mul [[a b] [c d]]
  [(- (* a c) (* b d)) (+ (* b c) (* a d))])

(defn div [[a b] [c d]]
  (let [denominator (+ (* c c) (* d d))]
    (map double [(/ (+ (* a c) (* b d)) denominator)
                 (/ (- (* b c) (* a d)) denominator)])))
 
 
 