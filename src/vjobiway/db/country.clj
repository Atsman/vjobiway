(ns vjobiway.db.country
  (:require [clojure.java.jdbc :as sql]))

(defn get-countries
  [database]
  (sql/with-db-connection [c (:connection database)]
    (sql/query c ["SELECT * FROM vjobiway.countries"])))

(defn get-country
  [database id]
  (sql/with-db-connection [c (:connection database)]
    (-> (sql/query c ["SELECT * FROM vjobiway.countries WHERE country_id = ?" id])
        (first))))
