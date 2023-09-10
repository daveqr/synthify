(ns api.core
  (:require [api.handler :as handler]
            [ring.adapter.jetty :as jetty]))

(defn -main []
  (jetty/run-jetty handler/handler {:port 5000}))
