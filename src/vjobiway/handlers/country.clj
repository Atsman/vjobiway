(ns vjobiway.handlers.country
  (:require [clojure.tools.logging :as log]
            [ring.util.http-response :as http-resp]
            [vjobiway.db.country :as cdb]))

(defn get-countries
  [app-component]
  (log/info "get-countries")
  (-> (:db-component app-component)
      (cdb/get-countries)
      (http-resp/ok)))

(defn get-country
  [app id]
  (log/info ";; get-country, id = " id)
  (let [country (cdb/get-country (:db-component app) id)]
    (if (= nil country)
      (http-resp/not-found)
      (http-resp/ok country))))
