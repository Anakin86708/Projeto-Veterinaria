package com.projeto.projetoveterinaria.view.tableModels;

import com.projeto.projetoveterinaria.model.Cliente;
import com.projeto.projetoveterinaria.model.Exame;
import java.util.List;

/**
 *
 * @author ariel
 */
public class ExameTableModel extends GenericTableModel<Exame> {

    public ExameTableModel(List<Exame> dados) {
        super(dados, new String[]{"Consulta", "Descrição exame"});
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Exame item = getItem(rowIndex);

        switch (columnIndex) {
            case 0:
                return item.getIdConsulta();
            case 1:
                return item.getDescricaoExame();
            default:
                throw new IndexOutOfBoundsException();
        }
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
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            default:
                throw new IndexOutOfBoundsException();
        }
    }

}
