package com.projeto.projetoveterinaria.controller.modal;

import com.projeto.projetoveterinaria.controller.Controller;
import com.projeto.projetoveterinaria.model.Consulta;
import com.projeto.projetoveterinaria.model.DAO.ConsultaDAO;
import com.projeto.projetoveterinaria.view.modals.ModalConsulta;
import com.projeto.projetoveterinaria.view.tableModels.ConsultaTableModel;

import javax.swing.*;
import java.awt.*;

public class ModalControllerConsulta implements IModalController{

    private final Frame parent;

    public ModalControllerConsulta(Frame parent) {
        this.parent = parent;
    }

    @Override
    public void adicionar(JTable table) {
        JDialog dialog = new ModalConsulta(parent, true);
        dialog.setVisible(true);
        setTableModel(table);
    }

    @Override
    public void editar(JTable table) {
        int selected = table.getSelectedRow();
        Controller.validateSelect(selected);

        Consulta data = ((ConsultaTableModel) table.getModel()).getItem(selected);
        JDialog dialog = new ModalConsulta(parent, true, data);
        dialog.setVisible(true);
        setTableModel(table);
    }

    @Override
    public void remover(JTable table) {
        int selected = table.getSelectedRow();
        Controller.validateSelect(selected);

        Consulta item = ((ConsultaTableModel) table.getModel()).getItem(selected);
        int confirmDialog = JOptionPane.showConfirmDialog(parent, "Deseja remover (" + item.getId() + ") permanentemente?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirmDialog == JOptionPane.YES_OPTION) {
            ConsultaDAO.getInstance().delete(item);
        }
        setTableModel(table);
    }

    @Override
    public void setTableModel(JTable table) {
        ConsultaTableModel model = new ConsultaTableModel(ConsultaDAO.getInstance().retrieveAll());
        table.setModel(model);
        model.fireTableDataChanged();
    }
}
