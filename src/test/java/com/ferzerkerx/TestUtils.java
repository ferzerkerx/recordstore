package com.ferzerkerx;

import com.ferzerkerx.model.Artist;
import com.ferzerkerx.model.Record;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

@UtilityClass
public final class TestUtils {

    public static Artist artist() {
        Artist artist = new Artist();
        artist.setName("foo");
        return artist;
    }

    public static Record audioRecord(@NonNull Artist artist) {
        Record audioRecord = new Record();
        audioRecord.setArtist(artist);
        audioRecord.setTitle("title");
        audioRecord.setYear("2004");
        return audioRecord;
    }

    public static Record defaultRecord() {
        return audioRecord(artist());
    }

    public static String resource(@NonNull String resourceName) {
        try {
            URL resource = TestUtils.class.getResource("/" + resourceName);
            if (Objects.isNull(resource)) {
                return StringUtils.EMPTY;
            }
            return new String(Files.readAllBytes(Paths.get(resource.getPath())));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
