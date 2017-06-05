(ns vjobiway.system
  (:require [com.stuartsierra.component :as component]
            [vjobiway.db.db-component :refer [db-component]]
            [vjobiway.handlers.http-component :refer [http-component]]
            [vjobiway.app-component :refer [app-component]]))

(defn app-system
  [config-options]
  (component/system-map
   :config-options config-options
   :db (db-component)
   :core (component/using
          (app-component config-options)
          {:db-component :db})
   :http (component/using
          (http-component (:http-port config-options))
          {:app-component :core})))
