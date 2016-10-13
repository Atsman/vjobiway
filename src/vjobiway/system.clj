(ns vjobiway.system
  (:require [com.stuartsierra.component :as component]
            [vjobiway.db.db-component :refer [db-component]]
            [vjobiway.handlers.http-component :refer [http-component]]
            [vjobiway.app-component :refer [app-component]]))

(defn get-db-config
  [config-options]
  (let [{:keys [db-host db-port db-name]} config-options]
    (list db-host db-port db-name)))

(defn app-system
  [config-options]
  (component/system-map
    :config-options config-options
    :db (apply db-component (get-db-config config-options))
    :core (component/using
            (app-component config-options)
            {:db-component :db})
    :http (component/using
            (http-component (:http-port config-options))
            {:app-component :core})))