(ns vjobiway.db.country-test
  (:require [clojure.test :refer :all]
            [clojure.tools.logging :as log]
            [vjobiway.db.country :as cdb]
            [vjobiway.util.fixtures-test :refer [with-db]]
            [vjobiway.util.helpers :as helpers]))

(deftest ^:integration db-get-country-test
  (testing "get-country should return nil for non existing country id"
    (with-db (fn [db] (is (nil? (cdb/get-country db 404))))))

  (testing "get-country should return country if country exists"
    (with-db (fn [db]
               (let [country (cdb/get-country db 1)]
                 (is (not (nil? country)))
                 (is (= "BY" (:country_code country))))))))

(deftest ^:integration db-get-countries-test
  (testing "get-countries should return list of all countries"
    (with-db (fn [db]
               (let [countries (cdb/get-countries db)]
                 (is (> (count countries) 0)))))))
