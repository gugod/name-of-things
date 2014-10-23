(ns name-of-things.core
  (:use ring.middleware.params)
  (:require [ring.util.response :as res]
            [ring.util.request  :as req]))

(def mem (atom {}))

(defn app [request]
  (let [kv-k (req/path-info request)
        kv-v (req/body-string request)]
    (println (str kv-k " - " kv-v))
    (case (:request-method request)
      :post
      (do
       (swap! mem assoc kv-k kv-v)
       (res/response ""))
      :get
      (res/response (@mem kv-k)))))
