(defproject api "0.1.0-SNAPSHOT"
  :description "Text-to-speech"
  :url "http://localhost:3000"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [compojure "1.7.0"]
                 [ring "1.10.0"]
                 [liberator "0.15.3"]]
  :main api.core
  :plugins [[lein-ring "0.12.6"]]
  :ring {:handler api.handler/app
         :nrepl {:start? true}
         :auto-reload? true}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}})
