package me.academeg;

import me.academeg.api.dao.AudioDao;
import me.academeg.api.dataSet.Audio;

import java.io.IOException;
import java.util.ArrayList;

public class TestMain {

    private void run(String[] args) {
        AudioDao audioDao = new AudioDao("d5d497699e29cb4780da8313960a31543e7becb03fe90a491d1c574a5fc4843e1c31d70c404c5aa30555f");
        try {
            ArrayList<Audio> audios = audioDao.get("36843575", 10, 0);
            audios.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new TestMain().run(args);
    }
}
