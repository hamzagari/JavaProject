package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        scene = new Scene(loadFXML("primary2"), 643, 400);
        String css = this.getClass().getResource("loginDesign.css").toExternalForm();
        String css2 = this.getClass().getResource("mainFormDesign.css").toExternalForm();
        String css3 = this.getClass().getResource("style.css").toExternalForm();

        stage.getIcons().add(new Image(
                "C:\\Users\\hamza\\Desktop\\cafeproject\\demo\\src\\main\\java\\com\\example\\image\\logo6.gif"));

        scene.getStylesheets().add(css);
        scene.getStylesheets().add(css2);
        scene.getStylesheets().add(css3);

        stage.setTitle(" Cafe ESTM ");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));

    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    public static void main(String[] args) {
        launch();
    }

}