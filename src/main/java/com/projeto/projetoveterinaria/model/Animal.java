package com.projeto.projetoveterinaria.model;

/**
 * @author Ariel Tadeu da Silva
 */
public class Animal {

    private final int id;
    private String nome;
    private int anoNasc;
    private int sexo;  // 0 - Macho; 1 - Fêmea
    private final int idEspecie;
    private final int idCliente;

    /**
     * @param id      identificação interna do BD
     * @param nome    nome
     * @param anoNasc ano de nascimento
     * @param sexo    sexo
     */
    public Animal(int id, String nome, int anoNasc, int sexo, int idEspecie, int idCliente) {
        this.id = id;
        this.nome = nome;
        this.anoNasc = anoNasc;
        this.sexo = sexo;
        this.idEspecie = idEspecie;
        this.idCliente = idCliente;
    }

    public int getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }


    public int getAnoNasc() {
        return anoNasc;
    }


    public int getSexo() {
        return sexo;
    }

    public int getIdEspecie() {
        return idEspecie;
    }

    public int getIdCliente() {
        return idCliente;
    }

    @Override
    public String toString() {
        return "Animal{" + "id=" + id + ", nome=" + nome + ", anoNasc=" + anoNasc + ", sexo=" + sexo + ", idEspecie=" + idEspecie + '}';
    }

}
