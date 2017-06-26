(ns vjobiway.handlers.city
  (:require [clojure.tools.logging :as log]
            [ring.util.http-response :as http-resp]
            [vjobiway.db.city :as cdb]))

(defn get-cities
  [app-component]
  (log/info ";; get-cities")
  (-> (:db-component app-component)
      (cdb/get-cities)
      (http-resp/ok)))

(defn get-city
  [app id]
  (log/info ";; get-city, id = " id)
  (let [city (cdb/get-city (:db-component app) id)]
    (if (nil? city)
      (http-resp/not-found)
      (http-resp/ok city))))
