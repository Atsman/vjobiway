(ns vjobiway.main
  (:gen-class)
  (:require [vjobiway.system :as system]
            [clojure.tools.logging :as log]
            [com.stuartsierra.component :as component]))

(defn -main [& args]
  (let [[app-port] args]
    (log/info ";; -main - starting app-system")
    (component/start
      (system/app-system {
        :http-port 9000
        :db-host "localhost"
        :db-port 5432 
        :db-name "jobs"
      }))))
