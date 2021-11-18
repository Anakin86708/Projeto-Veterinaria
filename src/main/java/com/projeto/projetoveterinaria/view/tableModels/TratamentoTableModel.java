package com.projeto.projetoveterinaria.view.tableModels;

import com.projeto.projetoveterinaria.model.Tratamento;

import java.util.Calendar;
import java.util.List;

public class TratamentoTableModel extends GenericTableModel<Tratamento> {

    private static final String[] nomeColunas = {"Nome", "Data entrada", "Data saída", "Animal", "Terminou"};
    private static final String nomeTabelaSQL = "tratamento";

    public TratamentoTableModel(List<Tratamento> dados) {
        super(dados, nomeColunas, nomeTabelaSQL);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Tratamento item = dados.get(rowIndex);

        if (item == null) {
            throw new NullPointerException();
        }

        return switch (columnIndex) {
            case 0 -> item.getNome();
            case 1 -> humanDateFormat(item.getDataEntrada());
            case 2 -> humanDateFormat(item.getDataSaida());
            case 3 -> item.getIdAnimal();
            case 4 -> item.getTerminou();
            default -> throw new IndexOutOfBoundsException();
        };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Tratamento item = dados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                item.setNome((String) aValue);
            case 1:
                item.setDataEntrada((Calendar) aValue);
            case 2:
                item.setDataSaida((Calendar) aValue);
            case 3:
                item.setIdAnimal((int) aValue);
            case 4:
                item.setTerminou((Boolean) aValue);
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> String.class;
            case 1, 2 -> Calendar.class;
            case 3 -> Integer.class;
            case 4 -> Boolean.class;
            default -> throw new IndexOutOfBoundsException();
        };
    }

}
