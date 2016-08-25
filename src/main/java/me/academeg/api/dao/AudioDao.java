package me.academeg.api.dao;

import me.academeg.api.VkData;
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
                .addQueryParameter("v", VkData.API_VER)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Response response = client.newCall(request).execute();

        ArrayList<Audio> audio = new ArrayList<>();
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonResponse = (JSONObject) ((JSONObject) parser.parse(response.body().string())).get("response");
            JSONArray array = (JSONArray) jsonResponse.get("items");
            for (Object anArray : array) {
                audio.add(Audio.parse((JSONObject) anArray));
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
