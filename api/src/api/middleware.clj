(ns api.middleware
  (:require [ring.util.response :refer [response]]))

(defn wrap-ignore-favicon-request [handler]
  (fn [request]
    (if (= (:uri request) "/favicon.ico")
      {:status 404}
      (handler request))))
