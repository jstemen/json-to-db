json-to-db
==========

A simple program that reads a text file containing an array JSON product objects and loads then into a database where they are accessible via a REST endpoint.

Compile the program and import the data into the db with:
mvn install exec:java -Dexec.mainClass=com.jaredstemen.blogspot.jsonimport.JsonFileImporterRunner

Start up the web server with:
mvn jetty:run

Checkout the Rest class for a list of available REST endpoints.

Use Chrome's Postman, or some other REST client, to test it out.