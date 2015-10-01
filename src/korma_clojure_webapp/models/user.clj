(ns korma-clojure-webapp.models.user
  (:require [korma-clojure-webapp.db.entities :as db])
  (:use korma.core)
  (:gen-class))

;;vector of all users
(defn get-all []
  (select db/users
          (with db/address)
          (fields :first :last :address.address :address.zip)))

;;will get sql query for the clojure code
(defn get-sql-outout [id]
  (sql-only
   (select  db/users
            (where (= :id id)))))

;;get user record
(defn get-user [id]
  (println id)
  (select  db/users
           (with db/address)
           (where (= :id id))))

(defn insert-user [user]
  (insert db/use
          (values user)))

(defn get-all-accounts []
  (select db/account))

(defn insert-account [account]
  (insert db/account
          (values account)))

(defn insert-address [addrs]
  (insert db/address
          (values addrs)))
