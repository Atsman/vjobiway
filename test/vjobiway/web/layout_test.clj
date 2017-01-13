(ns vjobiway.web.layout-test
  (:require [clojure.test :refer :all]
            [vjobiway.web.layout :refer [layout]]))

(deftest layout-test
  (testing "generate layout with empty child"
    (is (= (layout "") "<html><head><link href=\"public/style.css\" rel=\"stylesheet\" /></head><body></body></html>")))
  (testing "generate layout with child"
    (is (= (layout "test") "<html><head><link href=\"public/style.css\" rel=\"stylesheet\" /></head><body>test</body></html>"))))
