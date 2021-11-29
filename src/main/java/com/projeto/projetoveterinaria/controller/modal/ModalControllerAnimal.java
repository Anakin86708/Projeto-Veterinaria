package com.projeto.projetoveterinaria.controller.modal;

import com.projeto.projetoveterinaria.model.Animal;
import com.projeto.projetoveterinaria.model.DAO.AnimalDAO;
import com.projeto.projetoveterinaria.view.modals.ModalAnimal;
import com.projeto.projetoveterinaria.view.tableModels.AnimalTableModel;

import javax.swing.*;
import java.awt.*;

public class ModalControllerAnimal implements IModalController {

    private final Frame parent;

    public ModalControllerAnimal(Frame parent) {
        this.parent = parent;
    }

    @Override
    public void adicionar() {
        JDialog dialog = new ModalAnimal(parent, true);
        dialog.setVisible(true);
    }

    @Override
    public void editar(JTable table) {
        int selected = table.getSelectedRow();
        validateSelect(selected);

        Animal data = ((AnimalTableModel) table.getModel()).getItem(selected);
        JDialog dialog = new ModalAnimal(parent, true, data);
        dialog.setVisible(true);
    }

    private void validateSelect(int selected) {
        if (selected == -1)
            throw new IllegalArgumentException("Selecione um item válido.");
    }

    @Override
    public void remover(JTable table) {
        int selected = table.getSelectedRow();
        validateSelect(selected);

        Animal item = ((AnimalTableModel) table.getModel()).getItem(selected);
        int confirmDialog = JOptionPane.showConfirmDialog(parent, "Deseja remover (" + item.getId() + ") " + item.getNome() + " permanentemente?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirmDialog == JOptionPane.YES_OPTION) {
            AnimalDAO.getInstance().delete(item);
        }
    }
}
