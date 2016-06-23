package me.academeg;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import me.academeg.api.VkData;
import me.academeg.controller.AuthController;

public class Main extends Application {

    private VkData vkData;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        vkData = new VkData();
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
}
