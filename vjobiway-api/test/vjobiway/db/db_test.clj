(ns vjobiway.db.db-test
  (:require [clojure.test :refer :all]
            [vjobiway.db.db :as db]))

(deftest ^:integration db-test
  (testing "db/connect-to-database should connect to database and return connection"
    (is (not (= nil (db/connect-to-database db/db-spec)))))

  (testing "db/close-connection should close connection"
    (let [conn (db/connect-to-database db/db-spec)]
      (is (= nil (db/close-connection conn))))))
