(ns vjobiway.util.fixtures-test
  (:require [clojure.tools.logging :as log]
            [clojure.test :refer :all]
            [com.stuartsierra.component :as component]
            [vjobiway.db.db-component :refer [db-component]]))

(defn create-db-component []
  (db-component "localhost" 5432 "test"))

(defn fill-db [db])

(defn empty-db [db])

(defn with-db [f]
  (let [db-component (component/start (create-db-component))]
    (fill-db db-component)
    (try
      (f db-component)
      (finally 
        (empty-db db-component)
        (component/stop db-component)))))
