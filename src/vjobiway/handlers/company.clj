(ns vjobiway.handlers.company
  (:require [clojure.tools.logging :as log]
            [ring.util.http-response :as http-resp]
            [vjobiway.db.company :as cdb]))

(defn create-company
  [app-component company]
  (log/info "create-company" company)
  (-> (:db-component app-component)
      (cdb/create-company company)
      (http-resp/ok)))

(defn get-companies
  [app-component]
  (log/info "get-companies")
  (-> (:db-component app-component)
      (cdb/get-companies)
      (http-resp/ok)))

(defn get-company
  [app id]
  (log/info ";; get-company, id = " id)
  (let [company (cdb/get-company (:db-component app) id)]
    (if (= nil company)
      (http-resp/not-found)
      (http-resp/ok company))))
