package me.academeg.api.methods;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.academeg.api.VkData;
import me.academeg.api.model.Audio;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class AudioMethods {

    private String accessToken;

    public AudioMethods(String accessToken) {
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

        String body;
        try (Response response = client.newCall(request).execute()) {
            body = response.body().string();
        }

        ArrayList<Audio> audio = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode items = mapper.readTree(body).get("response").get("items");
            Iterator<JsonNode> elements = items.elements();
            while (elements.hasNext()) {
                audio.add(mapper.treeToValue(elements.next(), Audio.class));
            }
        } catch (IOException e) {
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
