(ns vjobiway.db.vacancy
  (:require [clojure.java.jdbc :as sql]))

(def VACANCIES "vjobiway.VACANCIES")

(defn get-vacancies
  [conn]
    (sql/with-db-connection [c conn] 
      (sql/query c ["select * from vjobiway.VACANCIES"])))

(defn get-vacancy
  [conn id]
    (sql/with-db-connection [c conn]
      (sql/query c ["select * from vjobiway.VACANCIES where id = $1", id])))
