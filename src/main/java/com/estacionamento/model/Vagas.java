package com.estacionamento.model;

import java.time.LocalDateTime;


public class Vagas
{

    private int id;
    private int numero;
    private Status status;
    private String placa;
    private LocalDateTime entrada;

    public Vagas( int numero, Status status, String placa, LocalDateTime entrada)
    {
        this.numero = numero;
        this.status = status;
        this.placa = placa;
        this.entrada = entrada;
    }

    public Vagas()
    {
    }

    public int id() {return id;}
    public Vagas setId(int id) { this.id = id; return this;}

    public int getNumero() {return numero;}
    public Vagas setNumero(int numero) {this.numero = numero;return this;}

    public Status getStatus() {return status;}
    public Vagas setStatus(Status status) {this.status = status;return this;}

    public String getPlaca() {return placa;}
    public Vagas setPlaca(String placa) {this.placa = placa; return this;}

    public LocalDateTime getEntrada() {return entrada;}
    public Vagas setEntrada(LocalDateTime entrada) {this.entrada = entrada; return this;}

}
