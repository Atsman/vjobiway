(ns vjobiway.handlers.http-component
  (:require [com.stuartsierra.component :as component]
            [clojure.tools.logging :as log]
            [ring.component.jetty :refer [jetty-server]]
            [vjobiway.handlers.app-routes :refer [app-routes]]))

(defn- jetty-component
  [app-component port]
  (jetty-server {:port port
                 :app {:handler (app-routes app-component)}}))

(defrecord HttpComponent [app-component port]
  component/Lifecycle

  (start [this]
    (log/info ";; HttpComponent - start")
    (let [jetty (jetty-component app-component port)]
      (component/start (assoc this :jetty jetty))))

  (stop [this]
    (log/info ";; HttpComponent - stop http server")
    (let [jetty (:jetty this)]
      (component/stop jetty)
      (assoc this :jetty nil))))

(defn http-component
  [port]
  (map->HttpComponent {:port port}))
