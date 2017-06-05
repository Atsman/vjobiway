(ns vjobiway.db.db-component-test
  (:require [clojure.test :refer :all]
            [clojure.tools.logging :as log]
            [com.stuartsierra.component :as component]
            [vjobiway.db.db-component :refer [db-component]]))

(deftest ^:integration db-component-test
  (testing "factory function"
    (let [db-comp (db-component)]
      (is (not (= nil db-comp)))))

  (testing "start"
    (let [db-comp (component/start (db-component))]
      (log/info db-comp)
      (is (:connection db-comp))
      (component/stop db-comp)))

  (testing "stop"
    (let [db-comp (-> (db-component)
                      (component/start)
                      (component/stop))]
      (is (= nil (:connection db-comp))))))
