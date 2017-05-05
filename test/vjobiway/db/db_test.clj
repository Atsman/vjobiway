(ns vjobiway.db.db-test
  (:require [clojure.test :refer :all]
            [vjobiway.db.db :refer [connect-to-database close-connection]]))

(def test-connection-params ["localhost" 5432 "test"])

(deftest ^:integration test-db
  (testing "connect-to-database"
    (is (not (= nil (apply connect-to-database test-connection-params)))))

  (testing "close-connection"
    (let [conn (apply connect-to-database test-connection-params)]
      (is (= nil (close-connection conn))))))
