(ns vjobiway.handlers.vacancy-handlers
  (:require [clojure.tools.logging :as log]
            [cheshire.core :refer :all]
            [vjobiway.db.vacancy :as vdb]))

(defn get-connection
  [app-component]
  (:connection (:db-component app-component)))

(defn get-vacancies
  [app-component]
  (log/info "get-vacancies")
  (-> (get-connection app-component)
      (vdb/get-vacancies)
      (generate-string)))

(defn get-vacancy
  [app-component id]
  (log/info "get-vacancy")
  (-> (get-connection app-component)
      (vdb/get-vacancy id)
      (generate-string)))
