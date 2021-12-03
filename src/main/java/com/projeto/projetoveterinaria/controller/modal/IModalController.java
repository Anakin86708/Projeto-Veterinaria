package com.projeto.projetoveterinaria.controller.modal;

import javax.swing.*;

public interface IModalController {
    void adicionar(JTable table);

    void editar(JTable table);

    void remover(JTable table);

    void setTableModel(JTable table);

}
