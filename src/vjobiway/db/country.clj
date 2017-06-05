(ns vjobiway.db.country
  (:require [clojure.java.jdbc :as sql]))

(def COUNTRIES "vjobiway.countries")

(defn create-country
  [database country]
  (sql/with-db-connection [c (:connection database)]
    (-> (sql/insert! c COUNTRIES country)
        (first))))

(defn get-countries
  [database]
  (sql/with-db-connection [c (:connection database)]
    (sql/query c ["SELECT * FROM vjobiway.countries"])))

(defn get-country
  [database id]
  (sql/with-db-connection [c (:connection database)]
    (-> (sql/query c ["SELECT * FROM vjobiway.countries WHERE id = ?" id])
        (first))))
