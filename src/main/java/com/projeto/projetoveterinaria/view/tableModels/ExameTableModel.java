package com.projeto.projetoveterinaria.view.tableModels;

import com.projeto.projetoveterinaria.model.Exame;
import java.util.List;

/**
 *
 * @author ariel
 */
public class ExameTableModel extends GenericTableModel<Exame> {

    private static final String[] nomeColunas = {"Consulta", "Descrição exame"};
    private static final String nomeTabelaSQL = "exame";


    public ExameTableModel(List<Exame> dados) {
        super(dados, nomeColunas, nomeTabelaSQL);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Exame item = getItem(rowIndex);

        if (item == null)
            throw new NullPointerException();

        return switch (columnIndex) {
            case 0 -> item.getIdConsulta();
            case 1 -> item.getDescricaoExame();
            default -> throw new IndexOutOfBoundsException();
        };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Exame item = dados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                item.setIdConsulta((int) aValue);
            case 1:
                item.setDescricaoExame((String) aValue);
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> Integer.class;
            case 1 -> String.class;
            default -> throw new IndexOutOfBoundsException();
        };
    }

}
