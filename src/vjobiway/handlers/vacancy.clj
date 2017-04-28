(ns vjobiway.handlers.vacancy
  (:require [clojure.tools.logging :as log]
            [cheshire.core :as json]
            [vjobiway.db.vacancy :as vdb]))

(defn save-vacancy
  [app-component vacancy]
  (log/info "save-vacancy" vacancy)
  (-> (:db-component app-component)
      (vdb/save-vacancy vacancy)
      (json/generate-string)))

(defn get-vacancies
  [app-component]
  (log/info "get-vacancies")
  (-> (:db-component app-component)
      (vdb/get-vacancies)
      (json/generate-string)))

(defn get-vacancy
  [app-component id]
  (log/info "get-vacancy")
  (-> (:db-component app-component)
      (vdb/get-vacancy id)
      (json/generate-string)))
