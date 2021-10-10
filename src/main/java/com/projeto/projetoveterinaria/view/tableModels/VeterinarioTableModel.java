package com.projeto.projetoveterinaria.view.tableModels;

import com.projeto.projetoveterinaria.model.Tratamento;
import com.projeto.projetoveterinaria.model.Veterinario;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class VeterinarioTableModel extends GenericTableModel<Veterinario> {
    public VeterinarioTableModel(List<Veterinario> dados) {
        super(dados, new String[]{"Nome", "Endereço", "Telefone"});
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Veterinario item = dados.get(rowIndex);

        if (item == null) {
            throw new NullPointerException();
        }

        switch (columnIndex) {
            case 0:
                return item.getNome();
            case 1:
                return item.getEndereco();
            case 2:
                return item.getTelefone();
            default:
                throw new IndexOutOfBoundsException();
        }
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
        switch (columnIndex) {
            case 0:
            case 1:
            case 2:
                return String.class;
            default:
                throw new IndexOutOfBoundsException();
        }
    }

}
