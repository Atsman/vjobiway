(ns vjobiway.db.db
  (:require [clojure.java.jdbc :as sql]
            [hikari-cp.core :as cp]
            [vjobiway.db.migration :as migration]))

(def db-spec
  {:auto-commit true
   :read-only false
   :pool-name "db-pool"
   :adapter "postgresql"
   :username "postgres"
   :password "mysecretpassword"
   :register-mbeans false})

(defn connect-to-database [host port db]
  {:datasource (cp/make-datasource (assoc db-spec :server-name host :port-number port :database-name db))})

(defn connect-and-create-schema [host port db]
  (let [connection (connect-to-database host port db)]
    (migration/init)
    connection))

(defn close-connection [datasource]
  (cp/close-datasource (:datasource datasource)))

