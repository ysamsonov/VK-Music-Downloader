package me.academeg.utils;

import me.academeg.api.VkData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class SettingsSaver {

    private static final String FILE_NAME = "settings";

    private static final String ACCESS_TOKEN_KEY = "access-token";
    private static final String USER_ID_KEY = "user-id";

    public static boolean isFileExist() {
        File file = new File(FILE_NAME);
        return file.exists();
    }

    public static VkData readSettings() throws IOException {
        File file = new File(FILE_NAME);
        VkData vkData = new VkData();
        try (FileInputStream inputStream = new FileInputStream(file)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            vkData.setAccessToken(properties.getProperty(ACCESS_TOKEN_KEY));
            vkData.setUserId(properties.getProperty(USER_ID_KEY));
        }
        return vkData;
    }

    public static void writeSettings(VkData vkData) {
        File file = new File(FILE_NAME);
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            Properties properties = new Properties();
            properties.setProperty(ACCESS_TOKEN_KEY, vkData.getAccessToken());
            properties.setProperty(USER_ID_KEY, vkData.getUserId());
            properties.store(outputStream, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
