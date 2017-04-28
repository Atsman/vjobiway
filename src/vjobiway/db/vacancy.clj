(ns vjobiway.db.vacancy
  (:require [clojure.java.jdbc :as sql]))

(def VACANCIES "vjobiway.VACANCIES")

(defn save-vacancy
  [database vacancy]
  (sql/with-db-connection [c (:connection database)]
    (sql/insert! c vacancy)))

(defn get-vacancies
  [database]
  (sql/with-db-connection [c (:connection database)]
    (sql/query c ["select * from vjobiway.VACANCIES"])))

(defn get-vacancy
  [database id]
  (sql/with-db-connection [c (:connection database)]
    (sql/query c ["select * from vjobiway.VACANCIES where id = $1", id])))
