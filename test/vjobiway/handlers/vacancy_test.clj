(ns vjobiway.handlers.vacancy-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [vjobiway.util.helpers :as helpers]
            [vjobiway.util.http :refer [test-app]]
            [vjobiway.db.vacancy :as vdb]
            [vjobiway.handlers.vacancy :as vacancy]))

(def test-cases [{:input "{\"vacancy_id\":1,\"title\":\"Senior Software Engineer\",\"description\":\"Vacancy description\",\"company_id\":1,\"city_id\":1,\"type\":\"full_tyme\",\"level\":\"Senior\"}"
                  :response {:status 201
                             :headers {"Location" "http://localhost/api/vacancies/1"
                                      "Content-Type" "application/json; charset=utf-8"}
                             :body "{\"vacancy_id\":1,\"title\":\"Senior Software Engineer\",\"description\":\"Vacancy description\",\"company_id\":1,\"city_id\":1,\"type\":\"full_tyme\",\"level\":\"Senior\"}"}}])

(deftest http-create-vacancy-test
  (testing "vh/create-vacancy should throw error on precondition"
    (helpers/should-throw #(vacancy/create-vacancy nil nil)))

  (testing "vh/create-vacancy should create vacancy and return 201"
    (with-redefs [vjobiway.db.vacancy/create-vacancy (fn [db vacancy] vacancy)]
      (doseq [test-case test-cases]
        (let [response (test-app (-> (mock/request :post "/api/vacancies")
                                     (mock/header "Content-Type" "application/json")
                                     (mock/body (:input test-case))))]
          (is (= (:response test-case) response)))))))
