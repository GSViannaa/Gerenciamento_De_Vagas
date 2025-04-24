package com.estacionamento.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EstacionamentoController
{
    @FXML private TextField campoNumero;
    @FXML private ComboBox<String> comboStatus;
    @FXML private TextField campoPlaca;
    @FXML private TextField campoEntrada;

    @FXML private TableView<?> tabelaVagas;
    @FXML private TableColumn<?, ?> colNumero;
    @FXML private TableColumn<?, ?> colStatus;
    @FXML private TableColumn<?, ?> colPlaca;
    @FXML private TableColumn<?, ?> colEntrada;

    public void initialize()
    {
        comboStatus.getItems().addAll("DISPONIVEL", "OCUPADA");
    }

    @FXML
    public void ocuparVaga()
    {

    }

    @FXML
    public void liberarVaga()
    {

    }
}
