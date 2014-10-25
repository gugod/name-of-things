(ns name-of-things.storage)

(def storage-root "/tmp/name-of-things")

(defn get [k]
  (slurp (str storage-root "/" k)))

(defn set [k v]
  (spit (str storage-root "/" k) v))

