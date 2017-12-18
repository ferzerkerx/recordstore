# RecordStore

[![Build Status](https://travis-ci.org/ferzerkerx/recordstore.svg?branch=master)](https://travis-ci.org/ferzerkerx/recordstore)
[![Quality Gate](https://sonarcloud.io/api/badges/gate?key=com.ferzerkerx%3Arecordstore-webapp)](https://sonarcloud.io/dashboard/index/com.ferzerkerx%3Arecordstore-webapp)

## Usage
3.mvn spring-boot:run
4. app will listen on port :8080

## Dependencies
 - maven 3+
 - jdk 8+

## Available services
GET
NOTE: Must send headers (you can use DHC in chrome to test this)
Accept: application/json

    - http://localhost:8080/artists.json --> lists all the registered artists
    - http://localhost:8080/artist/{id}/records.json --> lists all the records for the specified artist
    - http://localhost:8080/record/{id}.json --> gets the record for the specified id
    - http://localhost:8080/artist/{id}.json --> gets the artist for the specified id
    - http://localhost:8080/artist/search.json?name={name} --> finds an artist by the specified name

POST
NOTE: Must send headers (you can use DHC in chrome to test this)
Accept: application/json
Content-Type: application/json

    - http://localhost:8080/artists.json --> saves artist
    - http://localhost:8080/artist/{id}/record.json --> saves record for specified artist

DELETE
NOTE: Must send headers (you can use DHC in chrome to test this)
Accept: application/json
    - http://localhost:8080/record/{id}.json --> deletes the record for the specified id
    - http://localhost:8080/artist/{id}.json --> deletes the artist for the specified id (also deletes all records)

PUT
NOTE: Must send headers (you can use DHC in chrome to test this)
Accept: application/json
Content-Type: application/json
    - http://localhost:8080/record/{id}.json --> updates the record for the specified id
    - http://localhost:8080/artist/{id}.json --> updates the artist for the specified id
