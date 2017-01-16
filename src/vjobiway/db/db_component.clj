(ns vjobiway.db.db-component
  (:require [com.stuartsierra.component :as component]
            [clojure.tools.logging :as log]
            [vjobiway.db.db :refer [connect-and-create-schema close-connection]]))

(defrecord DbComponent [host port db connection]
  component/Lifecycle

  (start [component]
    (log/info ";; DbComponent - Starting database")
    (let [conn (connect-and-create-schema host port db)]
      (log/info ";; DbComponent - Database started")
      (assoc component :connection conn)))

  (stop [component]
    (log/info ";; Stopping database")
    (close-connection connection)
    (assoc component :connection nil)))

(defn db-component [host port db]
  (map->DbComponent {:host host :port port :db db}))
