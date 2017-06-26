(ns vjobiway.handlers.app-routes
  (:require [ring.util.response :refer [resource-response response]]
            [ring.middleware.json :as middleware]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [compojure.core :refer [routes GET POST]]
            [compojure.route :as route]
            [vjobiway.handlers.vacancy :as vacancy]
            [vjobiway.handlers.company :as company]
            [vjobiway.handlers.country :as country]
            [vjobiway.handlers.city :as city]
            [vjobiway.web.layout :refer [main-layout]]))

(defn app-routes
  [app-component]
  (->
   (routes
    (GET "/" [] "hello-world")
    (POST "/api/vacancies" {vacancy :body} (vacancy/create-vacancy app-component vacancy))
    (GET "/api/vacancies" [] (vacancy/get-vacancies app-component))
    (GET "/api/vacancies/:id" [id] (vacancy/get-vacancy app-component id))

    (POST "/api/companies" {company :body} (company/create-company app-component company))
    (GET "/api/companies" [] (company/get-companies app-component))
    (GET "/api/companies/:id" [id] (company/get-company app-component id))

    (GET "/api/countries" [] (country/get-countries app-component))
    (GET "/api/countries/:id" [id] (country/get-country app-component id))

    (GET "/api/cities" [] (city/get-cities app-component))
    (GET "/api/cities/:id" [id] (city/get-city app-routes id))
    (route/not-found "Not Found"))
   (middleware/wrap-json-response)
   (middleware/wrap-json-body {:keywords? true})
   (wrap-defaults api-defaults)))
