package com.ferzerkerx;

import com.ferzerkerx.model.Artist;
import com.ferzerkerx.model.Record;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.util.Objects.requireNonNull;

public final class TestUtils {

    private TestUtils() {
        throw new AssertionError();
    }

    public static Artist artist() {
        Artist artist = new Artist();
        artist.setName("foo");
        return artist;
    }

    public static Record record(Artist artist) {
        requireNonNull(artist);
        Record record = new Record();
        record.setArtist(artist);
        record.setTitle("title");
        record.setYear("2004");
        return record;
    }

    public static Record defaultRecord() {
        return record(artist());
    }

    public static String resource(String resourceName) {
        requireNonNull(resourceName);
        try {
            URL resource = TestUtils.class.getResource("/" + resourceName);
            return new String(Files.readAllBytes(Paths.get(resource.getPath())));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
