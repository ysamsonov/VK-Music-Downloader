package me.academeg.api;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VkData {

    private final static int APP_ID = 5518353;
    private final static String API_VER = "5.52";
    private final static String REDIRECT_URL = "https://oauth.vk.com/blank.html";
    private final static int SCOPE = 8;

    private String logInUrl;
    private String accessToken;
    private String userId;

    public VkData() {
        this.logInUrl = String.format("https://oauth.vk.com/authorize?client_id=%d&display=page&redirect_uri=%s" +
                "&scope=%d&response_type=token&v=%s", APP_ID, REDIRECT_URL, SCOPE, API_VER);
    }

    public String getLogInUrl() {
        return logInUrl;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static String parseAccessToken(String url) {
        Pattern p = Pattern.compile("(#access_token=)(\\w*)&");
        Matcher matcher = p.matcher(url);
        return matcher.find() ? matcher.group(2) : null;
    }
}
