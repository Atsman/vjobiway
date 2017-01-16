(ns vjobiway.util.fixtures
  (:require 
    [clojure.test :refer :all]
    [vjobiway.db.db-component :refer [db-component]]))

(defn create-db-component []
  (db-component "localhost" 28015 "test"))

(defn fill-db []
  )

(defn with-db [f]
  (let [db-component (create-db-component)]
    (component/start db-component)
    (fill-db db-component)
    (f db-component)
    (empty-db db-component)
    (component/stop db-component)))
