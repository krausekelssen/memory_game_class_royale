package ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sun.audio.*;

public class UI extends Application{

    public void start(Stage stage)throws Exception{
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ui/FXML_inicio.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        Platform.runLater(() -> root.requestFocus());
        Scene scene = new Scene(root);
        stage.setTitle("Clash Royale MEMORY CARD");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
        
    } 
}
