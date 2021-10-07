package com.projeto.projetoveterinaria.model;

/**
 * @author Ariel Tadeu da Silva
 */
public class Animal {

    private final int id;
    private String nome;
    private int anoNasc;
    private Sexo sexo;  // 0 - Macho; 1 - Fêmea
    private int idEspecie;
    private int idCliente;

    /**
     * @param id      identificação interna do BD
     * @param nome    nome
     * @param anoNasc ano de nascimento
     * @param sexo    sexo
     */
    public Animal(int id, String nome, int anoNasc, Sexo sexo, int idEspecie, int idCliente) {
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnoNasc() {
        return anoNasc;
    }

    public void setAnoNasc(int anoNasc) {
        this.anoNasc = anoNasc;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public int getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return "Animal{" + "id=" + id + ", nome=" + nome + ", anoNasc=" + anoNasc + ", sexo=" + sexo + ", idEspecie=" + idEspecie + '}';
    }

}
