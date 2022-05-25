(ns my-test
  (:require [clojure.test :refer [deftest is run-tests]]))

(deftest compare-empty
  (is (= "X" "")))