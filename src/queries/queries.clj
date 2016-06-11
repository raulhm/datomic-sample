; Query to find the name and country of the artirts that start in 1970

(defn run-query []
	(d/q '[:find ?name ?country
       :in $
       :where
       [?e :artist/startYear 1970]
       [?e :artist/country ?country]
       [?e :artist/name ?name]]
  db))

; Query for finding the name of the artirts of the United States that began in 1970

(defn run-query []
 	(d/q '[:find ?name
       :in $
       :where
       [?a :artist/name ?name]
       [?a :artist/country ?c]
       [?a :artist/startYear 1970]
       [?c :country/name "United States"]]
  db))

; Query for finding the duration of the songs shorter than 3 minutes

(defn run-query []
   	(d/q '[:find ?duration
       :in $
       :where
       [?t :track/duration ?duration]
       [(< ?duration 180000)]]
  db))

; Query for finding Spanish artists that began later than 1990
; and count number of record companies they have worked for.

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