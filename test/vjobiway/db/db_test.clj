(ns vjobiway.db.db-test
  (:require [clojure.test :refer :all]
            [vjobiway.db.db :as db]))

(deftest ^:integration test-db
  (testing "connect-to-database"
    (is (not (= nil (db/connect-to-database db/db-spec)))))

  (testing "close-connection"
    (let [conn (db/connect-to-database db/db-spec)]
      (is (= nil (db/close-connection conn))))))
