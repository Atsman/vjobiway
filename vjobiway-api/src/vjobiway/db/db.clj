(ns vjobiway.db.db
  (:require [clojure.java.jdbc :as sql]
            [hikari-cp.core :as cp]
            [migratus.core :as migratus]
            [environ.core :refer [env]]))

(def db-spec {:auto-commit true
              :read-only false
              :pool-name "db-pool"
              :adapter "postgresql"
              :register-mbeans false
              :url (env :database-url)})

(def migration-config {:store :database
                       :migration-dir "migrations/"
                       :init-script "init.sql"
                       :migration-table-name "vjobiway.migrations"
                       :db (env :database-url)})

(defn- create-or-migrate-schema [config]
  (migratus/init config)
  (migratus/migrate config))

(defn connect-to-database [config]
  {:datasource (cp/make-datasource config)})

(defn connect-and-create-or-migrate-schema []
  (create-or-migrate-schema migration-config)
  (connect-to-database db-spec))

(defn close-connection [datasource]
  (cp/close-datasource (:datasource datasource)))
