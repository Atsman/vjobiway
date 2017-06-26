(ns vjobiway.util.helpers
  (:require [clojure.test :refer :all]))

(defn is-thrown? [f]
  (try
    (do
      (f)
      false)
    (catch Throwable e
      (= e nil))))

(defn should-throw [f]
  (is (true? (is-thrown? f))))

(defn should-not-throw [f]
  (not (should-throw f)))
