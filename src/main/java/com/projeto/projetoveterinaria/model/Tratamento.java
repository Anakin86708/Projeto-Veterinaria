package com.projeto.projetoveterinaria.model;

import java.util.Calendar;

/**
 * @author ariel
 */
public class Tratamento {

    private final int id;
    private String nome;
    private Calendar dataEntrada;
    private Calendar dataSaida;
    private final int idAnimal;
    private boolean terminou;

    public Tratamento(int id ,String nome, Calendar dataEntrada, Calendar dataSaida, int idAnimal, boolean terminou) {
        this.id = id;
        this.nome = nome;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.idAnimal = idAnimal;
        this.terminou = terminou;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Calendar getDataEntrada() {
        return dataEntrada;
    }

    public Calendar getDataSaida() {
        return dataSaida;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public boolean isTerminou() {
        return terminou;
    }

    public void setTerminou(boolean terminou) {
        this.terminou = terminou;
    }

}
