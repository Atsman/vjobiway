(ns vjobiway.db.db-test
  (:require [clojure.test :refer :all]
            [vjobiway.db.db :refer :all]))

(deftest test-db
  (testing "connect-to-database"
    (is (not (= nil (connect-to-database "localhost" 28015 "test")))))

  (testing "close-connection"
    (let [conn (connect-to-database "localhost" 28015 "test")]
      (is (= :closed (close-connection conn))))))
