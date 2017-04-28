(ns vjobiway.handlers.app-routes
  (:require
   [ring.util.response :refer [resource-response response]]
   [ring.middleware.json :as middleware]
   [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
   [compojure.core :refer :all]
   [compojure.route :as route]
   [vjobiway.handlers.vacancy :as vacancy]
   [vjobiway.web.layout :refer [main-layout]]))

(defn app-routes
  [app-component]
  (-> 
    (routes
      (GET "/" [] (main-layout "hello-world"))
      (POST "/api/vacancies" vacancy (vacancy/save-vacancy app-component vacancy))
      (GET "/api/vacancies" [] (response (vacancy/get-vacancies app-component)))
      (GET "/api/vacancies/:id" [id] (vacancy/get-vacancy app-component id))
      (route/not-found "Not Found"))
    (middleware/wrap-json-body)
    (middleware/wrap-json-response)
    (wrap-defaults api-defaults)))