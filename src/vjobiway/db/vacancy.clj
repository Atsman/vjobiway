(ns vjobiway.db.vacancy
  (:require [rethinkdb.query :as r]))

(def VACANCIES "vacancies")

(defn get-vacancies
  [conn]
  (-> (r/table VACANCIES)
      (r/run conn)))

(defn get-vacancy
  [conn id]
  (-> (r/table VACANCIES)
      (r/get id)
      (r/run conn)))
