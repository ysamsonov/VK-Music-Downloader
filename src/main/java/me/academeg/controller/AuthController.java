package me.academeg.controller;

import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import me.academeg.api.VkData;
import me.academeg.utils.SettingsSaver;

import java.io.IOException;

public class AuthController {

    private VkData vkData;

    @FXML
    private WebView webView;

    @FXML
    private void initialize() {
        WebEngine engine = webView.getEngine();
        engine.load(vkData.getLogInUrl());
        engine.setJavaScriptEnabled(true);
        engine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED) {
                String accessToken = VkData.parseAccessToken(engine.getLocation());
                String userId = VkData.parseUserId(engine.getLocation());
                if (accessToken != null && userId != null) {
                    vkData.setAccessToken(accessToken);
                    vkData.setUserId(userId);
                    SettingsSaver.writeSettings(vkData);
                    openMusic();
                }
            }
        });
    }

    private void openMusic() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/music.fxml"));
            MusicController controller = new MusicController();
            controller.setVk(vkData);
            loader.setController(controller);
            Parent load = loader.load();

            Stage stage = new Stage();
            stage.setTitle("VK-Music Downloader");
            stage.setScene(new Scene(load));
            stage.getIcons().add(new Image(getClass().getResource("/icon.png").toString()));
            stage.show();

            ((Stage) webView.getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setVk(VkData vk) {
        this.vkData = vk;
    }
}
