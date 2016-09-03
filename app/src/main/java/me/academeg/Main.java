package me.academeg;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import me.academeg.ui.controller.AuthController;
import me.academeg.ui.controller.MusicController;
import me.academeg.utils.SettingsSaver;

import java.io.IOException;

public class Main extends Application {

    private VkData vkData;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws IOException {
        if (SettingsSaver.isFileExist()) {
            try {
                vkData = SettingsSaver.readSettings();
                openMusicController(primaryStage);
                return;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        vkData = new VkData();
        openAuthController(primaryStage);
    }

    private void openAuthController(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/auth.fxml"));
        AuthController controller = new AuthController();
        controller.setVk(vkData);
        loader.setController(controller);
        Parent load = loader.load();
        primaryStage.setTitle("VK-Music Downloader");
        primaryStage.setScene(new Scene(load));
        primaryStage.getIcons().add(new Image(getClass().getResource("/icon.png").toString()));
        primaryStage.show();
    }

    private void openMusicController(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/music.fxml"));
        MusicController controller = new MusicController();
        controller.setVk(vkData);
        loader.setController(controller);
        Parent load = loader.load();
        primaryStage.setTitle("VK-Music Downloader");
        primaryStage.setScene(new Scene(load));
        primaryStage.getIcons().add(new Image(getClass().getResource("/icon.png").toString()));
        primaryStage.show();
    }
}
