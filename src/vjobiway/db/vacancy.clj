(ns vjobiway.db.vacancy
  (:require [clojure.tools.logging :as log]
            [clojure.java.jdbc :as sql]))

(def VACANCIES "vjobiway.vacancies")

(defn create-vacancy
  [database vacancy]
  (sql/with-db-connection [c (:connection database)]
    (-> (sql/insert! c VACANCIES vacancy)
        (first))))

(defn get-vacancies
  [database]
  (sql/with-db-connection [c (:connection database)]
    (sql/query c ["select * from vjobiway.vacancies"])))

(defn get-vacancy
  [database id]
  (sql/with-db-connection [c (:connection database)]
    (-> (sql/query c ["SELECT * FROM vjobiway.vacancies WHERE vacancy_id = ?::integer" id])
        (first))))
