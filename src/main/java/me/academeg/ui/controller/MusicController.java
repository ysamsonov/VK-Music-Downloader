package me.academeg.ui.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.stage.DirectoryChooser;
import me.academeg.api.VkData;
import me.academeg.api.dao.AudioDao;
import me.academeg.api.dataSet.Audio;
import me.academeg.loader.DownloadManager;
import me.academeg.ui.listItem.AudioListItem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class MusicController {

    private VkData vkData;
    private String path;

    @FXML
    private ListView<AudioListItem> listView;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label currentPathLabel;

    @FXML
    private void initialize() {
        path = System.getProperty("user.home");
        currentPathLabel.setText(path);
        progressBar.setProgress(0);
        loadAudioList();
    }

    private void loadAudioList() {
        Thread thread = new Thread(() -> {
            AudioDao audioDao = new AudioDao(vkData.getAccessToken());
            try {
                ArrayList<Audio> audios = audioDao.get(vkData.getUserId(), 6000, 0);
                ArrayList<AudioListItem> list = new ArrayList<>(audios.size());
                audios.forEach((audio) -> list.add(new AudioListItem(audio)));
                listView.getItems().addAll(list);
                listView.setCellFactory(CheckBoxListCell.forListView(AudioListItem::downloadProperty));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    @FXML
    public void download(ActionEvent event) {
        ObservableList<AudioListItem> items = listView.getItems();
        LinkedList<Audio> audios = new LinkedList<>();
        for (AudioListItem item : items) {
            if (item.getDownload()) {
                audios.add(item.getAudio());
            }
        }
        DownloadManager downloadManager = new DownloadManager(audios);
        downloadManager.setProgressListener(val -> {
            progressBar.setProgress(val);
        });
        downloadManager.setPath(path);
        Thread thread = new Thread(downloadManager);
        thread.start();
    }

    @FXML
    public void chooseDirectory(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File(path));
        File file = directoryChooser.showDialog(listView.getScene().getWindow());
        if (file != null) {
            path = file.getAbsolutePath();
            currentPathLabel.setText(path);
        }
    }

    @FXML
    public void selectAll(ActionEvent event) {
        listView.getItems().forEach(audioListItem -> audioListItem.downloadProperty().set(true));
    }

    @FXML
    public void deselect(ActionEvent event) {
        listView.getItems().forEach(audioListItem -> audioListItem.downloadProperty().set(false));
    }

    public void setVk(VkData vk) {
        this.vkData = vk;
    }
}
