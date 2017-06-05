(ns vjobiway.db.vacancy-test
  (:require [clojure.test :refer :all]
            [clojure.tools.logging :as log]
            [vjobiway.db.vacancy :as vdb]
            [vjobiway.util.fixtures-test :refer [with-db]]))

(defn is-thrown? [f]
  (try
    (do
      (f)
      false)
    (catch Throwable e
      (= e nil))))

(defn should-throw [f]
  (is true (is-thrown? f)))

(defn should-not-throw [f]
  (not (should-throw f)))

(def test-cases [{:vacancy {:title "test vacancy"
                            :description "test description"
                            :company "EPAM"
                            :city "Cincinatty"
                            :type "Full type"
                            :level "Senior"}
                  :result {:error true}}])

(deftest ^:integration vacancy-db
  (testing "create-vacancy"
    (with-db (fn [db]
               (map (fn [{:keys [vacancy result]}]
                      (if (:error result)
                        (should-throw #(vdb/create-vacancy db vacancy))
                        (should-not-throw #(vdb/create-vacancy db vacancy))))
                    test-cases))))

  (testing "get-vacancies on empty database"
    (with-db (fn [db]
               (is (list) (vdb/get-vacancies db))))))
