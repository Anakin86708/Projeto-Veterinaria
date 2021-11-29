package com.projeto.projetoveterinaria.view.tableModels;

import com.projeto.projetoveterinaria.model.Animal;
import com.projeto.projetoveterinaria.model.Sexo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ariel
 */
public class AnimalTableModel extends GenericTableModel<Animal> {

    private static final String[] nomeColunas = {"Nome", "Ano de nascimento", "Sexo", "ID Espécie", "ID Cliente"};
    private static final String nomeTabelaSQL = "animal";

    public AnimalTableModel(List<Animal> dados) {
        super(dados, nomeColunas, nomeTabelaSQL);
    }

    public AnimalTableModel() {
        super(new ArrayList<>(), nomeColunas, nomeTabelaSQL);
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Animal item = dados.get(rowIndex);

        if (item == null)
            throw new NullPointerException();

        return switch (columnIndex) {
            case 0 -> item.getNome();
            case 1 -> item.getAnoNasc();
            case 2 -> item.getSexo();
            case 3 -> item.getIdEspecie();
            case 4 -> item.getIdCliente();
            default -> throw new IndexOutOfBoundsException();
        };
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
        return switch (columnIndex) {
            case 0 -> String.class;
            case 1, 3, 4 -> Integer.class;
            case 2 -> Sexo.class;
            default -> throw new IndexOutOfBoundsException();
        };
    }

}
