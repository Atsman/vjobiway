(defproject vjobiway "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :main vjobiway.main
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.logging "0.3.1"]
                 [log4j/log4j "1.2.17"]
		 [org.slf4j/slf4j-log4j12 "1.7.9"]
                 [org.clojure/java.jdbc "0.7.0-alpha1"]
                 [hikari-cp "1.7.5"]
                 [compojure "1.5.1"]
                 [ring/ring-core "1.5.0"]
                 [ring/ring-jetty-adapter "1.5.0"]
                 [ring/ring-defaults "0.2.1"]
                 [com.stuartsierra/component "0.3.2"]
                 [cheshire "5.6.3"]
                 [clj-soup/clojure-soup "0.1.3"]
                 [clj-time "0.12.0"]
                 [hiccup "1.0.5"]
                 [org.postgresql/postgresql "9.4.1212"]
		 [migratus "0.8.32"]]
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
