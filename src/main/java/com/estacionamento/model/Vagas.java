package com.estacionamento.model;

import java.time.LocalTime;

public class Vagas
{

    private int id;
    private int numero;
    private Status status;
    private String placa;
    private LocalTime entrada;

    public Vagas( int numero, Status status, String placa, LocalTime entrada)
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

    public int numero() {return numero;}
    public Vagas setNumero(int numero) {this.numero = numero;return this;}

    public Status status() {return status;}
    public Vagas setStatus(Status status) {this.status = status;return this;}

    public String placa() {return placa;}
    public Vagas setPlaca(String placa) {this.placa = placa; return this;}

    public LocalTime entrada() {return entrada;}
    public Vagas setEntrada(LocalTime entrada) {this.entrada = entrada; return this;}

}
