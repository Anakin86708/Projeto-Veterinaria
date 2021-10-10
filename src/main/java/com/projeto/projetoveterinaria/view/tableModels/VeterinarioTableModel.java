package com.projeto.projetoveterinaria.view.tableModels;

import com.projeto.projetoveterinaria.model.Veterinario;

import java.util.List;

public class VeterinarioTableModel extends GenericTableModel<Veterinario> {
    public VeterinarioTableModel(List<Veterinario> dados) {
        super(dados, new String[]{"Nome", "Endereço", "Telefone"});
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Veterinario item = dados.get(rowIndex);

        if (item == null)
            throw new NullPointerException();

        return switch (columnIndex) {
            case 0 -> item.getNome();
            case 1 -> item.getEndereco();
            case 2 -> item.getTelefone();
            default -> throw new IndexOutOfBoundsException();
        };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Veterinario item = dados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                item.setNome((String) aValue);
            case 1:
                item.setEndereco((String) aValue);
            case 2:
                item.setTelefone((String) aValue);
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0, 1, 2 -> String.class;
            default -> throw new IndexOutOfBoundsException();
        };
    }

}
