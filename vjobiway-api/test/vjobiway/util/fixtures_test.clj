(ns vjobiway.util.fixtures-test
  (:require [clojure.tools.logging :as log]
            [clojure.test :refer :all]
            [clojure.java.io :as io]
            [clojure.java.jdbc :as sql]
            [com.stuartsierra.component :as component]
            [vjobiway.db.db-component :refer [db-component]]))

(defn fill-db [db])

(defn empty-db [db]
  (try
    (sql/with-db-connection [c (:connection db)]
      (sql/execute! c (slurp (io/resource "migrations/drop.sql"))))
    (catch Throwable t (log/error "error while empting database"))))

(defn with-db [f]
  (let [dbc (component/start (db-component))]
    (fill-db dbc)
    (try
      (f dbc)
      (catch Throwable t (log/error t "error while executing body"))
      (finally
        (empty-db dbc)
        (component/stop dbc)))))
