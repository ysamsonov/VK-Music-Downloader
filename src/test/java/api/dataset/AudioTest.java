package api.dataset;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.academeg.api.dataSet.Audio;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class AudioTest {

    private String json;
    private long id;
    private long ownerId;
    private String artist;
    private String title;
    private long duration;
    private long date;
    private String url;
    private long lyricsId;
    private long genreId;

    public AudioTest(String json, long id, long ownerId, String artist, String title, long duration, long date,
                     String url, long lyricsId, long genreId) {
        this.json = json;
        this.id = id;
        this.ownerId = ownerId;
        this.artist = artist;
        this.title = title;
        this.duration = duration;
        this.date = date;
        this.url = url;
        this.lyricsId = lyricsId;
        this.genreId = genreId;
    }

    @Test
    public void parseTest() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Audio audio = mapper.readValue(json, Audio.class);
            assertEquals(audio.getId(), id);
            assertEquals(audio.getOwnerId(), ownerId);
            assertEquals(audio.getArtist(), artist);
            assertEquals(audio.getTitle(), title);
            assertEquals(audio.getDuration(), duration);
            assertEquals(audio.getDate(), date);
            assertEquals(audio.getUrl(), url);
            assertEquals(audio.getLyricsId(), lyricsId);
            assertEquals(audio.getGenreId(), genreId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][]{
                {"{\n" +
                        "  \"id\": 189157344,\n" +
                        "  \"owner_id\": -37273781,\n" +
                        "  \"artist\": \"Fr&#233;d&#233;ric Fran&#231;ois Chopin\",\n" +
                        "  \"title\": \"Nocturne Op. 9 №1\",\n" +
                        "  \"duration\": 327,\n" +
                        "  \"date\": 1360439875,\n" +
                        "  \"url\": \"https://psv4.vk.m...kHQxFT_rfaSsN8B1Hv3\",\n" +
                        "  \"lyrics_id\": 4427560,\n" +
                        "  \"genre_id\": 18\n" +
                        "}",
                        189157344,
                        -37273781,
                        "Fr&#233;d&#233;ric Fran&#231;ois Chopin",
                        "Nocturne Op. 9 №1",
                        327,
                        1360439875,
                        "https://psv4.vk.m...kHQxFT_rfaSsN8B1Hv3",
                        4427560,
                        18
                },
                {"{\n" +
                        "  \"id\": 189157344,\n" +
                        "  \"owner_id\": -37273781,\n" +
                        "  \"artist\": \"Fr&#233;d&#233;ric Fran&#231;ois Chopin\",\n" +
                        "  \"title\": \"Nocturne Op. 9 №1\",\n" +
                        "  \"duration\": 327,\n" +
                        "  \"date\": 1360439875,\n" +
                        "  \"url\": \"https://psv4.vk.m...kHQxFT_rfaSsN8B1Hv3\",\n" +
                        "  \"genre_id\": 18\n" +
                        "}",
                        189157344,
                        -37273781,
                        "Fr&#233;d&#233;ric Fran&#231;ois Chopin",
                        "Nocturne Op. 9 №1",
                        327,
                        1360439875,
                        "https://psv4.vk.m...kHQxFT_rfaSsN8B1Hv3",
                        0,
                        18
                }
        });
    }
}
