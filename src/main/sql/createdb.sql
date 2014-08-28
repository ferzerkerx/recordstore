CREATE DATABASE recordstore WITH OWNER = postgres;


CREATE TABLE artist(artist_id SERIAL NOT NULL,
                         name text NOT NULL,
                         PRIMARY KEY(artist_id));

CREATE TABLE record(record_id SERIAL NOT NULL,
                      artist_id INTEGER NOT NULL,
                      title TEXT NOT NULL,
                      year VARCHAR(4) NOT NULL,
                      PRIMARY KEY(record_id));


ALTER TABLE record ADD CONSTRAINT record_artist FOREIGN KEY (artist_id) REFERENCES artist;