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
      (is (= "Java developer" (:title vacancy)))
      (is (not (= nil (:description vacancy))))
      (is (= "ITOS" (:company vacancy)))
      (is (= "Минск" (:city vacancy)))
      (is (= "Полный день" (:type vacancy)))
      (is (= "Mid" (:level vacancy)))
      (is (= ["java" "Spring" "hibernate"] (:skills vacancy)))
      (is (= (t/date-time 2016 11 02) (:dueTo vacancy))))))
