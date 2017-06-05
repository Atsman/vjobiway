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
      (assoc this :jetty jetty)
      (component/start jetty)))

  (stop [this]
    (log/info ";; HttpComponent - stop http server")
    (log/info ";; HttpComponent content" this)
    (let [jetty (:jetty this)]
      (component/stop jetty)
      (assoc this :jetty nil)
      this)))

(defn http-component
  [port]
  (map->HttpComponent {:port port}))
