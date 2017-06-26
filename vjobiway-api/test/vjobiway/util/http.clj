(ns vjobiway.util.http
  (:require [vjobiway.app-component :refer [app-component]]
            [vjobiway.handlers.app-routes :refer [app-routes]]))

(def app-component-instance (app-component {:db-component {}}))
(def test-app (app-routes app-component-instance))
