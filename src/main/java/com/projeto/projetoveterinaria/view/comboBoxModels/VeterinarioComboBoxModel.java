package com.projeto.projetoveterinaria.view.comboBoxModels;

import com.projeto.projetoveterinaria.model.Veterinario;

import javax.swing.*;

public class VeterinarioComboBoxModel extends AbstractListModel<String> implements ComboBoxModel<String> {
     private final Veterinario[] dados;
     private String selected;

    public VeterinarioComboBoxModel(Veterinario[] dados) {
        this.dados = dados;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selected = (String) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selected;
    }

    @Override
    public int getSize() {
        return dados.length;
    }

    @Override
    public String getElementAt(int index) {
        return dados[index].getNome();
    }

    public Veterinario getVeterinarioAt(int index) {
        return dados[index];
    }
}
