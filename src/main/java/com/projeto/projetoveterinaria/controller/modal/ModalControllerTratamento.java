package com.projeto.projetoveterinaria.controller.modal;

import com.projeto.projetoveterinaria.controller.Controller;
import com.projeto.projetoveterinaria.model.DAO.TratamentoDAO;
import com.projeto.projetoveterinaria.model.Tratamento;
import com.projeto.projetoveterinaria.view.modals.ModalTratamento;
import com.projeto.projetoveterinaria.view.tableModels.TratamentoTableModel;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

public class ModalControllerTratamento implements IModalController {
    private final Frame parent;

    public ModalControllerTratamento(Frame parent) {
        this.parent = parent;
    }

    @Override
    public void adicionar(JTable table) {
        JDialog dialog = new ModalTratamento(parent, true);
        dialog.setVisible(true);
        setTableModel(table);
    }

    @Override
    public void editar(JTable table) {
        int selected = table.getSelectedRow();
        Controller.validateSelect(selected);

        Tratamento data = ((TratamentoTableModel) table.getModel()).getItem(selected);
        JDialog dialog = new ModalTratamento(parent, true, data);
        dialog.setVisible(true);
        setTableModel(table);
    }

    @Override
    public void remover(JTable table) {
        int selected = table.getSelectedRow();
        Controller.validateSelect(selected);

        Tratamento item = ((TratamentoTableModel) table.getModel()).getItem(selected);
        int confirmDialog = JOptionPane.showConfirmDialog(parent, "Deseja remover (" + item.getId() + ") " + item.getNome() + " permanentemente?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirmDialog == JOptionPane.YES_OPTION) {
            TratamentoDAO.getInstance().delete(item);
        }
        setTableModel(table);
    }

    @Override
    public void setTableModel(JTable table) {
        TableModel model = new TratamentoTableModel(TratamentoDAO.getInstance().retrieveAll());
        table.setModel(model);
    }
}
