(ns vjobiway.handlers.vacancy
  (:require [clojure.tools.logging :as log]
            [ring.util.http-response :as http-resp]
            [vjobiway.db.vacancy :as vdb]))

(defn create-vacancy
  [app-component vacancy]
  (log/info "create-vacancy" vacancy)
  (-> (:db-component app-component)
      (vdb/create-vacancy vacancy)
      (http-resp/ok)))

(defn get-vacancies
  [app-component]
  (log/info "get-vacancies")
  (-> (:db-component app-component)
      (vdb/get-vacancies)
      (http-resp/ok)))

(defn get-vacancy
  [app id]
  (log/info ";; get-vacancy, id = " id)
  (let [vacancy (vdb/get-vacancy (:db-component app) id)]
    (if (= nil vacancy)
      (http-resp/not-found)
      (http-resp/ok vacancy))))
