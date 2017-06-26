(ns vjobiway.handlers.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [vjobiway.util.http :refer [test-app]]))

(deftest http-basic-test
  (testing "h/hello-world should return hello-world text"
    (let [response (test-app (mock/request :get "/"))]
      (is (= 200 (:status response)))
      (is (= "hello-world" (:body response)))))

  (testing "h/not-found should return 404 on unknown route"
    (let [response (test-app (mock/request :get "/invalid"))]
      (is (= 404 (:status response))))))
