package com.projeto.projetoveterinaria.model;

/**
 * @author ariel
 */
public class Especie {

    private final int id;
    private String nomeEspecie;

    public Especie(int id, String nomeEspecie) {
        this.id = id;
        this.nomeEspecie = nomeEspecie;
    }

    public String getNomeEspecie() {

        return nomeEspecie;
    }

    public void setNomeEspecie(String nomeEspecie) {
        this.nomeEspecie = nomeEspecie;
    }

    public int getId() {
        return id;
    }
}
