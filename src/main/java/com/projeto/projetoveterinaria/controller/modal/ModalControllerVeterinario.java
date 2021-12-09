package com.projeto.projetoveterinaria.controller.modal;

import com.projeto.projetoveterinaria.controller.Controller;
import com.projeto.projetoveterinaria.model.DAO.VeterinarioDAO;
import com.projeto.projetoveterinaria.model.Veterinario;
import com.projeto.projetoveterinaria.view.modals.ModalVeterinario;
import com.projeto.projetoveterinaria.view.tableModels.VeterinarioTableModel;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

public class ModalControllerVeterinario implements IModalController{
    private final Frame parent;

    public ModalControllerVeterinario(Frame parent) {
        this.parent = parent;
    }

    @Override
    public void adicionar(JTable table) {
        JDialog dialog = new ModalVeterinario(parent, true);
        dialog.setVisible(true);
        setTableModel(table);
    }

    @Override
    public void editar(JTable table) {
        int selected = table.getSelectedRow();
        Controller.validateSelect(selected);

        Veterinario data = ((VeterinarioTableModel) table.getModel()).getItem(selected);
        JDialog dialog = new ModalVeterinario(parent, true, data);
        dialog.setVisible(true);
        setTableModel(table);
    }


    @Override
    public void remover(JTable table) {
        int selected = table.getSelectedRow();
        Controller.validateSelect(selected);

        Veterinario item = ((VeterinarioTableModel) table.getModel()).getItem(selected);
        int confirmDialog = JOptionPane.showConfirmDialog(parent, "Deseja remover (" + item.getId() + ") " + item.getNome() + " permanentemente?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirmDialog == JOptionPane.YES_OPTION) {
            VeterinarioDAO.getInstance().delete(item);
        }
        setTableModel(table);
    }

    @Override
    public void setTableModel(JTable table) {
        TableModel model = new VeterinarioTableModel(VeterinarioDAO.getInstance().retrieveAll());
        table.setModel(model);
    }
}
