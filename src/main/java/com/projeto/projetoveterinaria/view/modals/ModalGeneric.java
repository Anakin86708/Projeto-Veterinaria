package com.projeto.projetoveterinaria.view.modals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public abstract class ModalGeneric extends javax.swing.JDialog {
    public ModalGeneric(Frame owner, boolean modal) {
        super(owner, modal);
    }

    protected void feedback(String msg, String title, int type) {
        JOptionPane.showMessageDialog(this, msg, title, type);
    }

    protected abstract void createNewID();

    protected void exit() {
        this.setVisible(false);
        this.dispatchEvent(new WindowEvent(
                this, WindowEvent.WINDOW_CLOSING));
    }
}
