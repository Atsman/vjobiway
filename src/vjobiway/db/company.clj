(ns vjobiway.db.company
  (:require [clojure.java.jdbc :as sql]))

(def COMPANIES "vjobiway.companies")

(defn create-company
  [database company]
  (sql/with-db-connection [c (:connection database)]
    (first (sql/insert! c COMPANIES company))))

(defn get-companies
  [database]
  (sql/with-db-connection [c (:connection database)]
    (sql/query c ["select * from vjobiway.companies"])))

(defn get-company
  [database id]
  (sql/with-db-connection [c (:connection database)]
    (first (sql/query c ["SELECT * FROM vjobiway.companies WHERE id = ?" id]))))
