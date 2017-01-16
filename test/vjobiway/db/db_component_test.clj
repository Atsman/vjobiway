(ns vjobiway.db.db-component-test
  (:require [clojure.test :refer :all]
            [com.stuartsierra.component :as component]
            [vjobiway.db.db-component :refer [db-component]]))

(defn create-db-component []
  (db-component "localhost" 5432 "test"))

(deftest db-component-test
  (testing "factory function"
    (let [db-comp (create-db-component)]
      (is (not (= nil db-comp)))))

  (testing "start"
    (let [db-comp (component/start (create-db-component))]
      (is (:connection db-comp))))

  (testing "stop"
    (let [db-comp (component/stop (component/start (create-db-component)))]
      (is (= nil (:connection db-comp))))))
