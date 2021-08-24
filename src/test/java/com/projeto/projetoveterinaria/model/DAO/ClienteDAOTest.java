/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.projetoveterinaria.model.DAO;

import com.projeto.projetoveterinaria.model.Cliente;

import java.sql.PreparedStatement;
import java.sql.SQLOutput;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ariel
 */
public class ClienteDAOTest {

    private int id = 1;
    private ClienteDAO instance;
    private String nome = "Ariel";
    private String endereco = "123";
    private String cep = "321";
    private String email = "a@i.com";
    private String telefone = "123";
    private final Cliente expResult;

    public ClienteDAOTest() {
        this.instance = ClienteDAO.getInstance();
        expResult = new Cliente(id, nome, endereco, telefone, cep, email);
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @org.junit.jupiter.api.Test
    public void testCreate() {
        System.out.println("create");
        this.instance.create(nome, endereco, cep, email, telefone);
    }


    @org.junit.jupiter.api.Test
    public void testRetrieveAll() {
        System.out.println("retrieveAll");
        List<Cliente> expResult = null;
        List<Cliente> result = instance.retrieveAll();
        System.out.println(result);
//        assertEquals(expResult, result);
    }

    @org.junit.jupiter.api.Test
    public void testRetrieveById() {
        System.out.println("retrieveById");
        int id = 1;
        Cliente result = instance.retrieveById(id);
        assertEquals(expResult, result);
    }

    @org.junit.jupiter.api.Test
    public void testRetrieveBySimilarName() {
        System.out.println("retrieveBySimilarName");
        String likeNome = "Ari";
        List<Cliente> result = instance.retrieveBySimilarName(likeNome);
        assertEquals(expResult, result.get(0));
    }
    
}
