(ns korma-clojure-webapp.models.address
  (:require [korma-clojure-webapp.db.entities :as db])
  (:use korma.core))

(defn insert-address [addrs]
  (insert db/address
          (values addrs)))
