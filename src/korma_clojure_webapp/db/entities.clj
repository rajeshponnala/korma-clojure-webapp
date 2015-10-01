(ns korma-clojure-webapp.db.entities
  (:use korma.db)
  (:use korma.core)
  (:require [clojure.string :as str]))


(defdb devdb (postgres {:db "Korma"
                        :user "postgres"
                        :password "Design_20"
                        :host "localhost"
                        :port "5432"
                        }))

(declare users address email account posts)

(defentity users
  (pk :id)
  (table :users)
  (database devdb)
  (prepare (fn [{last :last :as v}]
             (if last
               (assoc v :last (str/upper-case last)) v)))
  ;; captilizes first field on select
  (transform (fn [{first :first :as v}]
               (if first
                 (assoc v :first (str/capitalize first)) v)))
  (has-one address)
  ;; this is ignoring the with clause
  (has-many email)
  (belongs-to account))

(defentity address
  (belongs-to users))

(defentity email
  (belongs-to users))

(defentity account
  (has-one users))

(defentity posts
  (many-to-many users :users_posts))
