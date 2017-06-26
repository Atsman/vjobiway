(defproject vjobiway "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  ;; :main vjobiway.main
  :dependencies [[org.clojure/clojure "1.8.0"]

                 [environ "1.1.0"]

                 ; Logging dependencies
                 [org.clojure/tools.logging "0.3.1"]
                 [log4j/log4j "1.2.17"]
                 [org.slf4j/slf4j-log4j12 "1.7.9"]

                 ; Database dependencies
                 [org.clojure/java.jdbc "0.7.0-alpha1"]
                 [org.postgresql/postgresql "9.4.1212"]
                 [hikari-cp "1.7.5"] ; Fastest connection pool
                 [migratus "0.8.32"] ; Database migrations

                 ; Http dependencies
                 [compojure "1.5.1"]
                 [ring/ring-core "1.5.0"]
                 [ring/ring-jetty-adapter "1.5.0"]
                 [ring/ring-defaults "0.2.1"]
                 [ring/ring-json "0.4.0"]
                 [ring-jetty-component "0.3.1"]
                 [metosin/ring-http-response "0.8.2"]

                 [hiccup "1.0.5"] ; HTML template engine
                 [com.stuartsierra/component "0.3.2"] ; Statefull components
                 [cheshire "5.6.3"] ; JSON parser
                 [clj-soup/clojure-soup "0.1.3"] ; jQuery like selectors
                 [clj-time "0.12.0"] ; Time parser/formatter
                 [danlentz/clj-uuid "0.1.7"]]

  :plugins [[lein-environ "1.1.0"]
            [lein-cljfmt "0.5.6"]
            [lein-ancient "0.6.10"]
            [venantius/ultra "0.5.1"]]

  :profiles {:dev {:env {:database-url "jdbc:postgresql://localhost/jobs?user=postgres&password=mysecretpassword"}
                   :dependencies [[org.clojure/tools.namespace "0.2.11"]
                                  [com.stuartsierra/component.repl "0.2.0"]
                                  [javax.servlet/servlet-api "2.5"]
                                  [ring/ring-mock "0.3.0"]]
                   :source-paths ["dev"]}

             :test {:env {:database-url "jdbc:postgresql://localhost/test?user=postgres&password=mysecretpassword"}}})
