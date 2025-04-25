package com.estacionamento;

import com.estacionamento.database.VagasInitializer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {
        VagasInitializer.popularVagas();

        Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Vaga de Estacionamento");
        stage.show();

    }

    public static  void main(String[] args)
    {
        launch(args);
    }
}
