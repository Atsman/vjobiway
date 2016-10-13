(ns vjobiway.handlers.app-routes
  (:require
            [ring.util.response :refer [resource-response]]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [vjobiway.handlers.vacancy-handlers :as vacancy]))

(defn app-routes
  [app-component]
  (routes
    (GET "/" [] (resource-response "index.html" {:root "public"}))
    (GET "/api/vacancies" [] (vacancy/get-vacancies app-component))
    (GET "/api/vacancies/:id" [id] (vacancy/get-vacancy app-component id))
    (route/resources "/")
    (route/not-found "Not Found")))
