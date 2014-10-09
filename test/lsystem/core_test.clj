(ns lsystem.core-test
  (:require [clojure.test :refer :all]
            [lsystem.core :refer :all]))

(def test-rules {\f "fff"
                 \g "ggg"
                 })

(deftest symbol-parsing
  (testing "defined rules"
    (is (= "fff" (parse-symbol test-rules \f)))
    (is (= "ggg" (parse-symbol test-rules \g))))

  (testing "undefined rules"
    (is (= \z (parse-symbol test-rules \z))))

  (testing "tastes good in a curry"
    (let [parse-test-rules (partial parse-symbol test-rules)]
      (is (= "fff" (parse-test-rules \f))))))

(run-tests)
