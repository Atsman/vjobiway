(ns vjobiway.db.vacancy-test
  (:require [clojure.test :refer :all]
            [vjobiway.db.vacancy :as vdb]
            [vjobiway.db.db-component-test :refer [create-db-component]]))

(deftest ^:integration vacancy-db
  (let [db-component (create-db-component)]
    (testing "getVacancies")))
