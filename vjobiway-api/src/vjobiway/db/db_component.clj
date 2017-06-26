(ns vjobiway.db.db-component
  (:require [com.stuartsierra.component :as component]
            [clojure.tools.logging :as log]
            [vjobiway.db.db :as db]))

(defrecord DbComponent [connection]
  component/Lifecycle

  (start [component]
    (log/info ";; DbComponent - Starting database")
    (if connection
      component
      (try
        (let [conn (db/connect-and-create-or-migrate-schema)]
          (log/info ";; DbComponent - Database started")
          (assoc component :connection conn))
        (catch Throwable t (log/error t "Error while connecting to database")))))

  (stop [component]
    (log/info ";; Stopping database this:" component)
    (if-not connection
      component
      (do
        (try (db/close-connection connection)
             (catch Throwable t (log/error t "Error while closing connection")))
        (assoc component :connection nil)))))

(defn db-component []
  (map->DbComponent {}))
