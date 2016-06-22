package me.academeg;

import me.academeg.api.VkData;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class TestMain {

    public static void main(String[] args) {
        new TestMain().run(args);
    }

    private void run(String[] args) {
        VkData vkData = new VkData();
        System.out.println(vkData.getLogInUrl());
    }
}
