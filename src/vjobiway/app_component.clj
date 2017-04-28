(ns vjobiway.app-component
  (:require [com.stuartsierra.component :as component]
            [clojure.tools.logging :as log]))

(defrecord AppComponent [options cache db-component]
  component/Lifecycle

  (start [this]
    (log/info ";; Starting app")
    this)

  (stop [this]
    (log/info ";; Stoping app")
    this))

(defn app-component
  [config-options]
  (map->AppComponent {:opitons config-options
                      :cache (atom {})}))