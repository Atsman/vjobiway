(ns vjobiway.scrap.scrap-devby-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [vjobiway.scrap.scrap-devby :refer [parse-vacancy-page]]
            [clj-time.core :as t]))

(defn get-test-page []
  (slurp (io/resource "test/dev.by.vacancy.html")))

(deftest scrap-devby-test
  (testing "parse vacancy page"
    (let [vacancy (parse-vacancy-page (get-test-page))]
      (is (= (:title vacancy) "Java developer"))
      (is (not (= (:description vacancy) nil)))
      (is (= (:company vacancy) "ITOS"))
      (is (= (:city vacancy) "Минск"))
      (is (= (:type vacancy) "Полный день"))
      (is (= (:level vacancy) "Mid"))
      (is (= (:skills vacancy) ["java" "Spring" "hibernate"]))
      (is (= (:dueTo vacancy) (t/date-time 2016 11 02))))))
