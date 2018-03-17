# RecordStore

[![Build Status](https://travis-ci.org/ferzerkerx/recordstore.svg?branch=master)](https://travis-ci.org/ferzerkerx/recordstore)
[![Quality Gate](https://sonarcloud.io/api/badges/gate?key=com.ferzerkerx%3Arecordstore-webapp)](https://sonarcloud.io/dashboard/index/com.ferzerkerx%3Arecordstore-webapp)

## Usage
mvn spring-boot:run
 - app will listen on port :8080

## Dependencies
 - maven 3+
 - jdk 8+

## Available services
GET
NOTE: Must send headers (you can use DHC in chrome to test this)
Accept: application/json

    - http://localhost:8080/artists --> lists all the registered artists
    - http://localhost:8080/artist/{id}/records --> lists all the records for the specified artist
    - http://localhost:8080/record/{id} --> gets the record for the specified id
    - http://localhost:8080/artist/{id} --> gets the artist for the specified id
    - http://localhost:8080/artist/search?name={name} --> finds an artist by the specified name

POST
NOTE: Must send headers (you can use DHC in chrome to test this)
Accept: application/json
Content-Type: application/json

    - http://localhost:8080/artists --> saves artist
    - http://localhost:8080/artist/{id}/record --> saves record for specified artist

DELETE
NOTE: Must send headers (you can use DHC in chrome to test this)
Accept: application/json
    - http://localhost:8080/record/{id} --> deletes the record for the specified id
    - http://localhost:8080/artist/{id} --> deletes the artist for the specified id (also deletes all records)

PUT
NOTE: Must send headers (you can use DHC in chrome to test this)
Accept: application/json
Content-Type: application/json
    - http://localhost:8080/record/{id} --> updates the record for the specified id
    - http://localhost:8080/artist/{id} --> updates the artist for the specified id
