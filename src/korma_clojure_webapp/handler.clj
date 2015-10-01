(ns korma-clojure-webapp.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.json :as ring-json]
            [ring.util.response	:as rr]
            [korma-clojure-webapp.models.model :refer [insert-account get-user
                                                       insert-user get-all
                                                       insert-address]]))

(defroutes app-routes
  (GET "/" [] (get-all))
  (GET "/user/:id" [id]
       (rr/response (get-user (read-string id))))
  (POST "/user" {body :body}
        (rr/response (insert-user body)))
  (POST "/account" {body :body}
        (rr/response (insert-account body)))
  (POST "/user/:id/address" {body :body}
        (rr/response (insert-address body)))
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      (wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))
      (ring-json/wrap-json-body)
      (ring-json/wrap-json-response)))
