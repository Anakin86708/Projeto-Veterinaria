package com.projeto.projetoveterinaria.view.tableModels;

import com.projeto.projetoveterinaria.model.Animal;
import com.projeto.projetoveterinaria.model.Sexo;
import com.projeto.projetoveterinaria.model.Tratamento;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TratamentoTableModel extends GenericTableModel<Tratamento> {
    public TratamentoTableModel(List<Tratamento> dados) {
        super(dados, new String[] {"Nome", "Data entrada", "Data saída", "Animal", "Terminou"});
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Tratamento item = dados.get(rowIndex);

        if (item == null) {
            throw new NullPointerException();
        }

        switch (columnIndex) {
            case 0:
                return item.getNome();
            case 1:
                return humanDateFormat(item.getDataEntrada());
            case 2:
                return humanDateFormat(item.getDataSaida());
            case 3:
                return item.getIdAnimal();
            case 4:
                return item.getTerminou();
            default:
                throw new IndexOutOfBoundsException();
        }
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
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
            case 2:
                return Calendar.class;
            case 3:
                return Integer.class;
            case 4:
                return Boolean.class;
            default:
                throw new IndexOutOfBoundsException();
        }
    }

}
