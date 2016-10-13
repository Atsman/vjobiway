(ns vjobiway.handlers.http-component
  (:require [com.stuartsierra.component :as component]
            [ring.adapter.jetty :as jetty]
            [vjobiway.handlers.app-routes :refer [app-routes]]))

(defrecord HttpComponent [http-server app-component port]
  component/Lifecycle

  (start [this]
    (assoc this :http-server
      (jetty/run-jetty (app-routes app-component) { :port port :join false })))

  (stop [this]
    (.stop http-server)
    this))

(defn http-component
  [port]
  (map->HttpComponent { :port port }))
