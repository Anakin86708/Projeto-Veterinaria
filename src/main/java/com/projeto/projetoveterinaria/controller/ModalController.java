package com.projeto.projetoveterinaria.controller;

import com.projeto.projetoveterinaria.model.Cliente;
import com.projeto.projetoveterinaria.model.DAO.ClienteDAO;
import com.projeto.projetoveterinaria.model.DAO.EspecieDAO;
import com.projeto.projetoveterinaria.model.Especie;

import javax.swing.*;
import java.sql.SQLException;

public class ModalController {

    public static void setModelComboEspecie(JComboBox<Especie> comboBox) {
        Especie[] model = EspecieDAO.getInstance().retrieveAll().toArray(Especie[]::new);
        comboBox.setModel(new DefaultComboBoxModel<>(model));
    }

    public static String sendData(Cliente data) throws SQLException {
        // Decidir se vai ser um insert ou update
        if (data.getId() == getNewIDCliente()) {
            // Insert
            ClienteDAO.getInstance().create(data.getNome(), data.getEndereco(), data.getCep(), data.getEmail(), data.getTelefone());
            return "Cliente criado com sucesso!";
        } else {
            // Update
            ClienteDAO.getInstance().update(data);
            return "Cliente atualizado com sucesso!";
        }
    }

    public static int getNewIDCliente() {
        return ClienteDAO.getInstance().getNextId();
    }
}
