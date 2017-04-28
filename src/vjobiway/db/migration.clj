(ns vjobiway.db.migration
  (:require [clojure.java.jdbc :as sql]
            [clojure.tools.logging :as log]
            [migratus.core :as migratus]))

(def config {:store :database
             :migration-dir "migrations/"
             :init-script "init.sql"
             :migration-table-name "vjobiway.MIGRATIONS"
             :db {:classname "org.postgresql.Driver"
                  :subprotocol "postgres"
                  :subname "//localhost/jobs"
                  :user "postgres"
                  :password "mysecretpassword"}})

             ;:db "postgres://postgres:mysecretpassword@localhost/jobs?sslmode=disable"})

(defn init []
  (migratus/init config))
