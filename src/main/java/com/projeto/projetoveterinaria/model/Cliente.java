package com.projeto.projetoveterinaria.model;

/**
 * @author Ariel Tadeu da Silva
 */
public class Cliente {

    private int id;
    private String nome;
    private String endereco;
    private String telefone;
    private String cep;
    private String email;


    /**
     * @param id identificação do animal no BD
     * @param nome nome
     * @param endereco endereco
     * @param telefone telefone
     * @param cep cep
     * @param email email
     */
    public Cliente(int id, String nome, String endereco, String telefone, String cep, String email) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cep = cep;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Cliente{" + " id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", telefone=" + telefone + ", cep=" + cep + ", email=" + email + + '}';
    }


}
