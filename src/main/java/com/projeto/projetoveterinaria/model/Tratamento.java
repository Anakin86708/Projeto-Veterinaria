package com.projeto.projetoveterinaria.model;

import java.util.Calendar;

/**
 * @author ariel
 */
public class Tratamento implements HasID {

    private final int id;
    private String nome;
    private Calendar dataEntrada;
    private Calendar dataSaida;
    private int idAnimal;
    private boolean terminou;

    public Tratamento(int id, String nome, Calendar dataEntrada, Calendar dataSaida, int idAnimal, boolean terminou) {
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Calendar getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Calendar dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Calendar getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Calendar dataSaida) {
        this.dataSaida = dataSaida;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public boolean getTerminou() {
        return terminou;
    }

    public void setTerminou(boolean terminou) {
        this.terminou = terminou;
    }

}
