package me.academeg.loader;

import me.academeg.api.dataSet.Audio;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class DownloadManager implements Runnable {

    private List<Audio> dataSet;
    private OnProgressListener progressListener;

    public DownloadManager(List<Audio> dataSet) {
        this.dataSet = dataSet;
    }

    public DownloadManager(List<Audio> dataSet, OnProgressListener progressListener) {
        this.dataSet = dataSet;
        this.progressListener = progressListener;
    }

    @Override
    public void run() {
        float countDownloaded = 0;
        int maxCount = dataSet.size();
        for (Audio audio : dataSet) {
            if (Thread.currentThread().isInterrupted()) {
                return;
            }

            String filename = String.format("%s - %s.mp3", audio.getArtist(), audio.getTitle());
            try {
                downloadFile(filename, audio.getUrl());
            } catch (IOException e) {
                e.printStackTrace();
            }
            countDownloaded++;
            if (progressListener != null) {
                progressListener.update(countDownloaded / maxCount);
            }
        }
    }

    public void setProgressListener(OnProgressListener progressListener) {
        this.progressListener = progressListener;
    }

    private void downloadFile(String filename, String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection httpConnection = (HttpURLConnection) (url.openConnection());
        try (
                BufferedInputStream in = new BufferedInputStream(httpConnection.getInputStream());
                FileOutputStream out = new FileOutputStream(filename)
        ) {
            int saveSize = 0;
            float fileSize = httpConnection.getContentLength();
            byte buffer[] = new byte[1024];
            int count;
            while ((count = in.read(buffer, 0, 1024)) != -1) {
                out.write(buffer, 0, count);
                saveSize += count;
            }
        }
    }

    private String normalizeName(String name) {
        return name.replaceAll("[^a-zA-Z0-9.-]", "_");
    }
}
