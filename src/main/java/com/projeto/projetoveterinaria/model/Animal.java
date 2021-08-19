package com.projeto.projetoveterinaria.model;

/**
 *
 * @author Ariel Tadeu da Silva
 */
public class Animal {

    private int id;
    private String nome;
    private int idade;
    private int sexo;  // 0 - Macho; 1 - Fêmea

    /**
     *
     * @param id
     * @param nome
     * @param idade
     * @param sexo
     */
    public Animal(int id, String nome, int idade, int sexo) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
    }

    public int getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }


    public int getIdade() {
        return idade;
    }


    public int getSexo() {
        return sexo;
    }

    @Override
    public String toString() {
        return "Animal{" + "id=" + id + ", nome=" + nome + ", idade=" + idade + ", sexo=" + sexo + '}';
    }

}
