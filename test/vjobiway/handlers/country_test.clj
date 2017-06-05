(ns vjobiway.handlers.country-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [vjobiway.app-component :refer [app-component]]
            [vjobiway.db.country :as cdb]
            [vjobiway.handlers.app-routes :refer [app-routes]]))

(def app-component-instance (app-component {:db-component {}}))
(def app (app-routes app-component-instance))

(deftest http-get-countries-test
  (testing "ch/get-countries should return countries"
    (with-redefs [vjobiway.db.country/get-countries (fn [db] [{:id 1 :country_code "BY"}])]
      (let [response (app (mock/request :get "/api/countries"))]
        (is (= 200 (:status response)))
        (is (= "[{\"id\":1,\"country_code\":\"BY\"}]" (:body response)))))))

(deftest http-get-country-by-id-test
  (testing "ch/get-country-by-id should return 400 if id is valid"
    (with-redefs [vjobiway.db.country/get-country (fn [db id] {:id id})]
      (let [response (app (mock/request :get "/api/countries/test"))]
        (is (= 400 (:status response)))
        (is (= "{\"error\":\"id must be an integer\"}" (:body response)))))))
