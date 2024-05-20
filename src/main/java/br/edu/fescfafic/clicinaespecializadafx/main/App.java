package br.edu.fescfafic.clicinaespecializadafx.main;

import br.edu.fescfafic.clicinaespecializadafx.HelloApplication;
import br.edu.fescfafic.clicinaespecializadafx.dao.SendSMSDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("resources/br/edu/fescfafic/clicinaespecializadafx/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Clínica Especializada");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
