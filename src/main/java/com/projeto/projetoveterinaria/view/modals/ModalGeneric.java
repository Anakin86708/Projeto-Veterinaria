package com.projeto.projetoveterinaria.view.modals;

import java.awt.*;

public abstract class ModalGeneric<T> extends javax.swing.JDialog {

    public ModalGeneric(Frame owner, boolean modal) {
        super(owner, modal);
    }

    public ModalGeneric(Frame owner, boolean modal, T data) {
        super(owner, modal);

        setupData(data);
    }

    protected abstract void setupData(T data);
}
