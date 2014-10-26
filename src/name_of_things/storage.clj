(ns name-of-things.storage
  (:require [clojure.java.io   :as io]
            [clojure.java.jdbc :as jdbc]))

(def db
  {:classname "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname "/tmp/name-of-things.db"
   })

(defn create-db []
  (try (jdbc/db-do-commands
        db
        (jdbc/create-table-ddl "NameOfThings"
                               [:name  :varchar "PRIMARY KEY"]
                               [:thing :varchar "NOT NULL"]
                               [:created_at :timestamp "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"]))
       (catch Exception e (println e))))

(if (not (.exists (io/file "/tmp/name-of-things.db")))
  (create-db))

(defn get [k]
  (:thing (first (jdbc/query db ["select thing from NameOfThings where name = ?" k]))))

(defn set [k v]
  (jdbc/insert! db "NameOfThings" {:name k :thing v}))

