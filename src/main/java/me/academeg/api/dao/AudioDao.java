package me.academeg.api.dao;

import me.academeg.api.dataSet.Audio;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public class AudioDao {

    private String url;

    private String accessToken;

    public AudioDao(String accessToken) {
        this.accessToken = accessToken;
    }

    public ArrayList<Audio> get(String ownerId, int count, int offset) throws IOException {
        OkHttpClient client = new OkHttpClient();

        HttpUrl url = getUrl()
                .addQueryParameter("owner_id", ownerId)
                .addQueryParameter("access_token", accessToken)
                .addQueryParameter("count", String.valueOf(count))
                .addQueryParameter("offset", String.valueOf(offset))
                .build();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Response response = client.newCall(request).execute();

        ArrayList<Audio> audio = null;
        try {
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) ((JSONObject) parser.parse(response.body().string())).get("response");
            audio = new ArrayList<>(array.size());
            for (int i = 1; i < array.size(); i++) {
                audio.add(Audio.parse((JSONObject) array.get(i)));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return audio;
    }

    private HttpUrl.Builder getUrl() {
        return new HttpUrl.Builder()
                .scheme("https")
                .host("api.vk.com")
                .addPathSegment("method")
                .addPathSegment("audio.get");
    }
}
