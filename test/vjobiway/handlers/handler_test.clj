(ns vjobiway.handlers.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [vjobiway.util.http :refer [test-app]]))

(deftest http-basic-test
  (testing "h/hello-world should return hello-world text"
    (let [response (test-app (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "hello-world"))))

  (testing "h/not-found should return 404 on unknown route"
    (let [response (test-app (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))
