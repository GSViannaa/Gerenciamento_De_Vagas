package com.estacionamento.controller;

import com.estacionamento.dao.VagasDAO;
import com.estacionamento.model.Vagas;
import com.estacionamento.model.Status;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class EstacionamentoController
{
    @FXML private TextField campoNumero;
    @FXML private TextField campoPlaca;
    @FXML private TextField campoEntrada;
    @FXML private Label relogioEntrada;


    @FXML private TableView<Vagas> tabelaVagas;
    @FXML private TableColumn<Vagas, Integer> colNumero;
    @FXML private TableColumn<Vagas, Status> colStatus;
    @FXML private TableColumn<Vagas, String> colPlaca;
    @FXML private TableColumn<Vagas, LocalDateTime> colEntrada;


    private VagasDAO vagasDAO = new VagasDAO();

    public void initialize()
    {
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        colEntrada.setCellValueFactory(new PropertyValueFactory<>("entrada"));

        colStatus.setCellFactory(column -> {
            return new TableCell<Vagas, Status>()
            {

                @Override
                protected void updateItem(Status status, boolean empty)
                {
                    super.updateItem(status, empty);

                    if (empty || status == null)
                    {
                        setText(null);
                        setStyle("");
                    }
                    else
                    {
                        setText(status.name());

                        if (status == Status.DISPONIVEL)
                        {
                            setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
                        }
                        else if (status == Status.OCUPADA)
                        {
                            setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                        }
                        else
                        {
                            setStyle("-fx-text-fill: black;");
                        }
                    }
                }
            };
        });


        carregarTabela();
        iniciarRelogio();

    }

    private void carregarTabela()
    {
        ObservableList<Vagas> lista = FXCollections.observableArrayList(vagasDAO.listarTodas());
        tabelaVagas.setItems(lista);
    }


    @FXML
    public void ocuparVaga()
    {

        try
        {
            int numero = Integer.parseInt(campoNumero.getText());
            String placa = campoPlaca.getText();
            LocalDateTime entrada = LocalDateTime.now();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            campoEntrada.setText(entrada.format(formatter));

            if(placa.isEmpty())
            {
                showAlert(Alert.AlertType.ERROR, "Error", "A placa não pode estar vazia");
                return;
            }

            boolean sucesso = VagasDAO.ocuparVaga(numero, placa, entrada);

            if(sucesso)
            {
                showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Vaga ocupada com sucesso!");
                carregarTabela();
                limparCampos();
            }
            else
            {
                showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao ocupar a vaga. Verifique se o número é válido.");
            }
        }
        catch (NumberFormatException e)
        {
            showAlert(Alert.AlertType.ERROR, "Erro", "Número da vaga inválido.");
        }

    }

    @FXML
    public void liberarVaga()
    {
        try {
            int numero = Integer.parseInt(campoNumero.getText());

            boolean sucesso = vagasDAO.liberarVaga(numero);

            if (sucesso)
            {
                showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Vaga liberada com sucesso!");

                carregarTabela();
                limparCampos();
            }
            else
            {
                showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao liberar a vaga. Verifique se o número é válido.");
            }

        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Número da vaga inválido.");
        }

    }
    private void limparCampos()
    {
        if (campoNumero != null) campoNumero.clear();
        if (campoPlaca != null) campoPlaca.clear();
    }

    private void showAlert(Alert.AlertType tipo, String titulo, String mensagem)
    {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
    private void iniciarRelogio()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            relogioEntrada.setText(LocalDateTime.now().format(formatter));
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
