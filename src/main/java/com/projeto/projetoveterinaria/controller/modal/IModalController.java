package com.projeto.projetoveterinaria.controller.modal;

import javax.swing.*;

public interface IModalController {
    public abstract void adicionar();

    void editar(JTable table);

    public abstract void remover(JTable table);
}
