import me.academeg.api.dataSet.Audio;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class AudioTest {

    private String data;

    private String artist;
    private String title;
    private long duration;
    private String lyricsId;
    private String url;

    public AudioTest(String data, String artist, String title, long duration, String lyricsId, String url) {
        this.data = data;
        this.artist = artist;
        this.title = title;
        this.duration = duration;
        this.lyricsId = lyricsId;
        this.url = url;
    }

    @Test
    public void parseTest() {
        try {
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(data);
            Audio audio = Audio.parse(object);
            assertEquals(audio.getArtist(), artist);
            assertEquals(audio.getTitle(), title);
            assertEquals(audio.getDuration(), duration);
            assertEquals(audio.getLyricsId(), lyricsId);
            assertEquals(audio.getUrl(), url);
        } catch (ParseException e) {
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
                        "  \"lyrics_id\": \"4427560\",\n" +
                        "  \"genre_id\": 18\n" +
                        "}",
                        "Fr&#233;d&#233;ric Fran&#231;ois Chopin",
                        "Nocturne Op. 9 №1",
                        327,
                        "4427560",
                        "https://psv4.vk.m...kHQxFT_rfaSsN8B1Hv3"
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
                        "Fr&#233;d&#233;ric Fran&#231;ois Chopin",
                        "Nocturne Op. 9 №1",
                        327,
                        null,
                        "https://psv4.vk.m...kHQxFT_rfaSsN8B1Hv3"
                }
        });
    }
}
