package me.academeg.utils;

import me.academeg.api.VkData;

import java.io.*;

public class SettingsSaver {

    private static final String FILE_NAME = "settings";

    public static boolean isFileExist() {
        File file = new File(FILE_NAME);
        return file.exists();
    }

    public static VkData readSettings() throws IOException, ClassNotFoundException {
        File file = new File(FILE_NAME);
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
        VkData settings = (VkData) inputStream.readObject();
        inputStream.close();
        fileInputStream.close();
        return settings;
    }

    public static void writeSettings(VkData vkData) {
        File file = new File(FILE_NAME);
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file))) {
            stream.writeObject(vkData);
            stream.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
