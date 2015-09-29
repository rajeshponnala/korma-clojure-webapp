(ns korma-clojure-webapp.models.email
  (:require [korma-clojure-webapp.db.entities :as db])
  (:use korma.core))


(defn get-all []
  (select db/email))

(defn insert-email [email]
  (insert db/email
          (values email)))
