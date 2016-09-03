package me.academeg.loader;

import me.academeg.model.Audio;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class DownloadManager implements Runnable {

    private List<Audio> dataSet;
    private String path;
    private OnProgressListener progressListener;
    private OnFinishListener finishListener;

    public DownloadManager(List<Audio> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public void run() {
        float countDownloaded = 0;
        int maxCount = dataSet.size();
        for (Audio audio : dataSet) {
            if (Thread.currentThread().isInterrupted()) {
                if (finishListener != null) {
                    finishListener.onFinish();
                }
                return;
            }

            String filename = String.format("%s - %s.mp3", audio.getArtist(), audio.getTitle());
            String pathForDownload = path != null ? String.format("%s/%s", path, filename) : filename;
            try {
                downloadFile(pathForDownload, audio.getUrl());
            } catch (IOException e) {
                e.printStackTrace();
            }
            countDownloaded++;
            if (progressListener != null) {
                progressListener.update(countDownloaded / maxCount);
            }
        }
        if (finishListener != null) {
            finishListener.onFinish();
        }
    }

    public void setProgressListener(OnProgressListener progressListener) {
        this.progressListener = progressListener;
    }

    public void setFinishListener(OnFinishListener finishListener) {
        this.finishListener = finishListener;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private void downloadFile(String filename, String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection httpConnection = (HttpURLConnection) (url.openConnection());
        try (
                BufferedInputStream in = new BufferedInputStream(httpConnection.getInputStream());
                FileOutputStream out = new FileOutputStream(filename)
        ) {
//            int saveSize = 0;
//            float fileSize = httpConnection.getContentLength();
            byte buffer[] = new byte[1024];
            int count;
            while ((count = in.read(buffer, 0, 1024)) != -1) {
                out.write(buffer, 0, count);
//                saveSize += count;
            }
        }
    }

    @SuppressWarnings("unused")
    protected String normalizeName(String name) {
        return name.replaceAll("[^a-zA-Z0-9.-]", "_");
    }
}
