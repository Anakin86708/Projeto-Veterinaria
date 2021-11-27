package com.projeto.projetoveterinaria.controller;

import com.projeto.projetoveterinaria.model.DAO.EspecieDAO;
import com.projeto.projetoveterinaria.model.Especie;

import javax.swing.*;

public class ModalController {

    public static void setModelComboEspecie(JComboBox<Especie> comboBox) {
        Especie[] model = EspecieDAO.getInstance().retrieveAll().toArray(Especie[]::new);
        comboBox.setModel(new DefaultComboBoxModel<>(model));
    }
}
