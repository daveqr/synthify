(ns api.handler
  (:require [compojure.core :refer [defroutes GET ANY]]
            [ring.middleware.params :refer [wrap-params]]
            [api.middleware :refer [wrap-ignore-favicon-request]]
            [ring.util.response :refer [response]]
            [liberator.core :refer [resource defresource]]
            ))

(defresource hello-world []
             :available-media-types ["text/plain"]
             :handle-ok "Hello, world!")

(defresource example []
             :available-media-types ["text/plain"]
             :handle-ok "example!!!")


(defresource parameter [txt]
             :available-media-types ["text/plain"]
             :handle-ok (fn [_] (format "The text is %s" txt)))

(defresource hello-george
             :available-media-types ["text/plain" "text/html"]
             :handle-ok (fn [context] (case (get-in context [:representation :language])
                                        "en" "Hello George!"
                                        "bg" "Zdravej, Georgi"
                                        "Hello!"))
             :available-languages ["en" "bg" "*"])

(defroutes app
           (ANY "/hello" [] (resource :available-media-types ["text/html"]
                                    :handle-ok (fn [ctx]
                                                 (format "<html>It's %d milliseconds since the beginning of the epoch."
                                                         (System/currentTimeMillis)))))
           (ANY "/hello2" [] (hello-world))
           (ANY "/example" [] (example))
           (ANY "/bar/:txt" [txt] (parameter txt))
           (ANY "/hello-george" [] hello-george)
           (ANY "/secret" []
             (resource :available-media-types ["text/html"]
                       :exists? (fn [ctx]
                                  (= "tiger" (get-in ctx [:request :params "word"])))
                       :handle-ok "You found the secret word!"
                       :handle-not-found "Uh, that's the wrong word. Guess again!")))

(def handler
  (-> app
      wrap-ignore-favicon-request
      wrap-params))
