(ns vjobiway.db.db
  (:require [rethinkdb.core :as rc]
            [rethinkdb.query :as r]))

(defn connect-to-database [host port db]
  (rc/connect :host host :port port :db db))

(defn close-connection [connection]
  (rc/close connection))

(defn create-db [connection db-name]
  (r/run (r/db-create db-name) connection))
