(ns queries.core)

(require '[datomic.api :as d])

(def uri "datomic:free://localhost:4334/mbrainz")
(def conn (d/connect uri))

(def db (d/db conn))

(defn run-query []
    (d/q '[:find ?name (count ?label)
       :in $
       :where
       [?r :release/artists ?a]
       [?r :release/label ?l]
       [?l :label/name ?label]
       [?r :release/name ?release-name]
       [?a :artist/name ?name]
       [?a :artist/startYear ?year]
       [?a :artist/country ?c]
       [?c :country/name "Spain"]
       [(> ?year 1990)]]
  db))

(defn changed-label
  [[name labels]]
  (> labels 1))

; This program finds Spanish artits that began later than 1990, count
; the record companies they have worked for and filter artist that have worked
; for more than one.

(defn -main []
  (println (filter changed-label (run-query))))