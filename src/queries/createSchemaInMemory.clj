(ns queries.core)

(require '[datomic.api :as d])

(def uri "datomic:mem://lab")
(d/create-database uri)

(def conn (d/connect uri))
(def db (d/db conn))

(def dog-schema  [{:db/id (d/tempid :db.part/db)
                   :db/ident :dog/name
                   :db/valueType :db.type/string
                   :db/cardinality :db.cardinality/one
                   :db/unique :db.unique/identity
                   :db/doc "Name of the Dog"
                   :db.install/_attribute :db.part/db}
                  {:db/id (d/tempid :db.part/db)
                   :db/ident :dog/breed
                   :db/valueType :db.type/string
                   :db/cardinality :db.cardinality/one
                   :db/doc "Breed of the Dog"
                   :db.install/_attribute :db.part/db}
                  {:db/id (d/tempid :db.part/db)
                   :db/ident :dog/favorite-treat
                   :db/valueType :db.type/string
                   :db/cardinality :db.cardinality/one
                   :db/doc "Dog's Favorite Treat to Eat"
                   :db.install/_attribute :db.part/db}])

(def owner-schema [{:db/id (d/tempid :db.part/db)
                    :db/ident :owner/name
                    :db/valueType :db.type/string
                    :db/cardinality :db.cardinality/one
                    :db/unique :db.unique/identity
                    :db/doc "Name of the Owner"
                    :db.install/_attribute :db.part/db}
                   {:db/id (d/tempid :db.part/db)
                    :db/ident :owner/dogs
                    :db/valueType :db.type/ref
                    :db/cardinality :db.cardinality/many
                    :db/doc "Dogs of the Owner"
                    :db.install/_attribute :db.part/db}])

(d/transact conn dog-schema)
(d/transact conn owner-schema)

(d/transact conn [{:db/id (d/tempid :db.part/user)
                   :owner/name "Bob"
                   :owner/dogs [{:db/id (d/tempid :db.part/user)
                                 :dog/name "Fluffy"
                                 :dog/breed "Poodle"
                                 :dog/favorite-treat "Cheese"}
                                {:db/id (d/tempid :db.part/user)
                                 :dog/name "Fido"
                                 :dog/breed "Mix"
                                 :dog/favorite-treat "Bone"}]}
                  {:db/id (d/tempid :db.part/user)
                   :owner/name "Lucy"
                   :owner/dogs [{:db/id (d/tempid :db.part/user)
                                 :dog/name "Tiny"
                                 :dog/breed "Great Dane"
                                 :dog/favorite-treat "Cheese"}]}])

(defn query []
  (d/q '[:find ?name
         :in $
         :where
         [?e :dog/name ?name]] (d/db conn)))


(defn -main []
	(println (query)))