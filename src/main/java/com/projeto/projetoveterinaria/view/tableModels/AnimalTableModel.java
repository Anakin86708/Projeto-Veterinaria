package com.projeto.projetoveterinaria.view.tableModels;

import com.projeto.projetoveterinaria.model.Animal;
import com.projeto.projetoveterinaria.model.Cliente;
import com.projeto.projetoveterinaria.model.Sexo;
import java.util.List;

/**
 *
 * @author ariel
 */
public class AnimalTableModel extends GenericTableModel<Animal> {

    public AnimalTableModel(List<Animal> dados) {
        super(dados, new String[]{"Nome", "Ano de nascimento", "Sexo", "ID Espécie", "ID Cliente"});
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Animal item = dados.get(rowIndex);

        if (item == null) {
            throw new NullPointerException();
        }

        switch (columnIndex) {
            case 0:
                return item.getNome();
            case 1:
                return item.getAnoNasc();
            case 2:
                return item.getSexo();
            case 3:
                return item.getIdEspecie();
            case 4:
                return item.getIdCliente();
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Animal item = dados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                item.setNome((String) aValue);
            case 1:
                item.setAnoNasc((int) aValue);
            case 2:
                item.setSexo((Sexo) aValue);
            case 3:
                item.setIdEspecie((int) aValue);
            case 4:
                item.setIdCliente((int) aValue);
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
            case 3:
            case 4:
                return Integer.class;
            case 2:
                return Sexo.class;
            default:
                throw new IndexOutOfBoundsException();
        }
    }

}
