(ns name-of-things.storage)

(def mem (atom {}))

(defn get [k]
  (@mem k))

(defn set [k v]
  (swap! mem assoc k v))

