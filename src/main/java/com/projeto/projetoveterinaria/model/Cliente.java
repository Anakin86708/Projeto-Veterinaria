package com.projeto.projetoveterinaria.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ariel Tadeu da Silva
 */
public class Cliente {

    private final int id;
    private String nome;
    private String endereco;
    private String telefone;
    private String cep;
    private String email;
    
    private final List<Animal> animais;

    /**
     *
     * @param id
     * @param nome
     * @param endereco
     * @param telefone
     * @param cep
     * @param email
     */
    public Cliente(int id, String nome, String endereco, String telefone, String cep, String email) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cep = cep;
        this.email = email;
        this.animais = new ArrayList<>();
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
    
    public void adicinarAnimal(Animal novoAnimal) {
        this.animais.add(novoAnimal);
    }
    
    
    
    public List<Animal> obterAnimais() {
        return new ArrayList<>(this.animais);
    }

    @Override
    public String toString() {
        return "Cliente{" + " id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", telefone=" + telefone + ", cep=" + cep + ", email=" + email + ", \nanimais=" + animais + '}';
    }
    

}
