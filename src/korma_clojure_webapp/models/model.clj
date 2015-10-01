(ns korma-clojure-webapp.models.model
  (:require [korma-clojure-webapp.db.entities :as db])
  (:use korma.core))

;;vector of all users
(defn get-all []
  (select db/users
          (with db/address)
          (with db/account)
          (fields :first :last :address.address
                  :address.zip :account.accountname)))

;;will get sql query for the clojure code
(defn get-sql-outout [id]
  (sql-only
   (select  db/users
            (where (= :id id)))))

;;get user record
(defn get-user [id]
  (select  db/users
           (with db/address)
           (with db/account)
           (where (= :id id))))

(defn insert-user [user]
  (insert db/users
          (values user)))

(defn get-all-accounts []
  (select db/account))

(defn insert-account [account]
  (println account)
  (insert db/account
          (values account)))

(defn insert-address [addrs]
  (insert db/address
          (values addrs)))
