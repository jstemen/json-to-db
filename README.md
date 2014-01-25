json-to-db
==========

A simple program that reads a text file containing an array JSON product objects and loads then into a database where they are accessible via a REST endpoint.


Getting It Up and Running
=========================

1. Change the json.file.location property in the propertyFile.properties file to point to the JSON text file on your local file system.

2. Compile the program and import the data into the db with:
mvn install exec:java -Dexec.mainClass=com.jaredstemen.blogspot.jsonimport.JsonFileImporterRunner

3.  Start up the web server with:
mvn jetty:run

4.  Checkout the Rest class for a list of available REST endpoints.

5.  Use Chrome's Postman, or some other REST client, to test it out.