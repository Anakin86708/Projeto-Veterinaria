package com.projeto.projetoveterinaria.controller;

import com.projeto.projetoveterinaria.model.DAO.AnimalDAO;
import com.projeto.projetoveterinaria.model.DAO.ClienteDAO;
import com.projeto.projetoveterinaria.view.PanelPadrao;
import com.projeto.projetoveterinaria.view.tableModels.AnimalTableModel;
import com.projeto.projetoveterinaria.view.tableModels.ClienteTableModel;
import java.awt.Component;

/**
 *
 * @author ariel
 */
public class Controller {
    
    private static Component instancePanelAnimal;

    public static Component getPanelAnimal() {
        if (instancePanelAnimal == null)
            instancePanelAnimal = new PanelPadrao("Animais", new AnimalTableModel(AnimalDAO.getInstance().retrieveAll()));
        return instancePanelAnimal;
    }
}
