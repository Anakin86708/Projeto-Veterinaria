package com.projeto.projetoveterinaria.controller.modal;

import com.projeto.projetoveterinaria.controller.Controller;
import com.projeto.projetoveterinaria.model.DAO.ExameDAO;
import com.projeto.projetoveterinaria.model.Exame;
import com.projeto.projetoveterinaria.view.modals.ModalExame;
import com.projeto.projetoveterinaria.view.tableModels.ExameTableModel;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

public class ModalControllerExame implements IModalController{
    private final Frame parent;

    public ModalControllerExame(Frame parent) {
        this.parent = parent;
    }

    @Override
    public void adicionar(JTable table) {
        JDialog dialog = new ModalExame(parent, true);
        dialog.setVisible(true);
        setTableModel(table);
    }

    @Override
    public void editar(JTable table) {
        int selected = table.getSelectedRow();
        Controller.validateSelect(selected);

        Exame data = ((ExameTableModel) table.getModel()).getItem(selected);
        JDialog dialog = new ModalExame(parent, true, data);
        dialog.setVisible(true);
        setTableModel(table);
    }

    @Override
    public void remover(JTable table) {
        int selected = table.getSelectedRow();
        Controller.validateSelect(selected);

        Exame item = ((ExameTableModel) table.getModel()).getItem(selected);
        int confirmDialog = JOptionPane.showConfirmDialog(parent, "Deseja remover (" + item.getId() + ") permanentemente?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirmDialog == JOptionPane.YES_OPTION) {
            ExameDAO.getInstance().delete(item);
        }
        setTableModel(table);
    }

    @Override
    public void setTableModel(JTable table) {
        TableModel model = new ExameTableModel(ExameDAO.getInstance().retrieveAll());
        table.setModel(model);
    }
}
