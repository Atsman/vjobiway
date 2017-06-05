(ns vjobiway.db.city
  (:require [clojure.java.jdbc :as sql]))

(def CITIES "vjobiway.cities")

(defn create-city
  [database city]
  (sql/with-db-connection [c (:connection database)]
    (-> (sql/insert! c CITIES city)
        (first))))

(defn get-cities
  [database]
  (sql/with-db-connection [c (:connection database)]
    (sql/query c ["select * from vjobiway.cities"])))

(defn get-city
  [database id]
  (sql/with-db-connection [c (:connection database)]
    (-> (sql/query c ["SELECT * FROM vjobiway.cities WHERE id = ?" id])
        (first))))
