(ns vjobiway.main
  (:gen-class)
  (:require [vjobiway.system :as system]
            [com.stuartsierra.component :as component]))

(defn -main [& args]
  (let [[app-port] args]
    (component/start
      (system/app-system {
        :http-port 9000
        :db-host "localhost"
        :db-port 28015
        :db-name "jobs"
      }))))
