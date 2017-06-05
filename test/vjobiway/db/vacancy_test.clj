(ns vjobiway.db.vacancy-test
  (:require [clojure.test :refer :all]
            [clojure.tools.logging :as log]
            [vjobiway.db.vacancy :as vdb]
            [vjobiway.util.fixtures-test :refer [with-db]]
            [vjobiway.util.helpers :as helpers]))

(def test-cases [{:vacancy {:title "test vacancy"
                            :description "test description"
                            :company "EPAM"
                            :city "Cincinatty"
                            :type "Full type"
                            :level "Senior"}
                  :result {:error true}}])

(deftest ^:integration db-vacancy-test
  (testing "vdb/create-vacancy should create vacancy"
    (with-db (fn [db]
               (map (fn [{:keys [vacancy result]}]
                      (if (:error result)
                        (helpers/should-throw #(vdb/create-vacancy db vacancy))
                        (helpers/should-not-throw #(vdb/create-vacancy db vacancy))))
                    test-cases))))

  (testing "vdb/get-vacancies should return empty list on empty database"
    (with-db (fn [db]
               (is (list) (vdb/get-vacancies db))))))
