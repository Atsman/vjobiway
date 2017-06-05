(ns vjobiway.app-component
  (:require [com.stuartsierra.component :as component]
            [clojure.tools.logging :as log]))

(defrecord AppComponent [db-component]
  component/Lifecycle

  (start [this]
    (log/info ";; Starting app")
    this)

  (stop [this]
    (log/info ";; Stoping app")
    this))

(defn app-component 
  ([] (app-component {}))
  ([config] (map->AppComponent {})))
