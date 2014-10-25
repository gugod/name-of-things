(ns name-of-things.core
  (:use ring.middleware.params)
  (:require [clojure.tools.logging :as log]
            [ring.util.response :as res]
            [ring.util.request  :as req]
            [name-of-things.storage :as storage]
            ))

(defn app [request]
  (let [k (req/path-info request)
        v (req/body-string request)]
    (case (:request-method request)
      :post
      (do
        (log/info "STORE REQUEST")
        (storage/set k v)
        (res/response ""))
      :get
      (do
        (log/info "GET REQUEST")
        (res/response (storage/get k))))
    ))
