# 1. Datomic first approach

[Datomic](http://datomic.com) is a database of flexible, time-based
facts, supporting queries and joins with elastic scalability, and ACID
transactions.

[MusicBrainz](http://musicbrainz.org) is an open music encyclopedia
that collects music metadata and makes it available to the public.
The MusicBrainz dataset makes a great example database for learning,
evaluating, or testing Datomic.

## 1.1 For testing querying

### Download Datomic

[Datomic download page](https://my.datomic.com/downloads/free)

### Unzip datomic

    unzip datomic-free-$VERSION.zip

### Start the transactor

    cd /path/unzippedDatomic
    bin/transactor config/samples/free-transactor-template.properties

### Download a sample db

Download a
[subset of the mbrainz database from 1968-1973](http://s3.amazonaws.com/mbrainz/datomic-mbrainz-1968-1973-backup-2015-02-11.tar) without some of the schemas used in this example (i.e. label), but useful for a first approach to queries

    wget http://s3.amazonaws.com/mbrainz/datomic-mbrainz-1968-1973-backup-2015-02-11.tar -O mbrainz.tar
    tar -xvf mbrainz.tar

 or a more complete[subset of mbrainz database](http://s3.amazonaws.com/mbrainz/datomic-mbrainz-backup-20130611.tar)

	# 2.8 GB, md5 4e7d254c77600e68e9dc71b1a2785c53
    wget http://s3.amazonaws.com/mbrainz/datomic-mbrainz-backup-20130611.tar
    tar -xvf datomic-mbrainz-backup-20130611.tar

### Restore the db

    # prints progress -- ~1,000 segments in restore
    bin/datomic restore-db file:///path/to/backup/mbrainz-1968-1973 datomic:free://localhost:4334/mbrainz-1968-1973

### db Schema
[Entities schema](https://github.com/Datomic/mbrainz-sample/wiki/Schema)
[Edn Schema](https://github.com/Datomic/mbrainz-sample/blob/master/schema.edn)
[Diagram schema](http://github.com/Datomic/mbrainz-sample/raw/master/relationships.png)

### Run the samples in clojure

In this repository you will find a core.clj that runs a program and queries.clj that has some sample queries for filtering, counting and making a "join" sample.

Just go to the project directory and
	lein run

If you want to test some other queries, just change it in the core.clj file.

## 1.2 Creating a schema sample

In this repository there's a /queries/createSchemaInMemory.clj that brings you a sample for creating an schema and test it running queries to it.

For testing it:

Replace the core.clj content for the content of /queries/createSchemaInMemory.clj and run it via
	lein run

## Thanks
We would like to thank the MusicBrainz project for defining and compiling a great dataset, and for making it freely available.

## Referencies
[Datomic blog](http://blog.datomic.com/2013/07/datomic-musicbrainz-sample-database.html)

[Datomic doc](http://docs.datomic.com/)

[Github sample](https://github.com/Datomic/mbrainz-sample)

[Datalog querying tutorial](http://www.learndatalogtoday.org/)