package com.projeto.projetoveterinaria.view.tableModels;

import com.projeto.projetoveterinaria.model.Cliente;
import com.projeto.projetoveterinaria.model.Consulta;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ariel
 */
public class ConsultaTableModel extends GenericTableModel<Consulta> {

    public ConsultaTableModel(List<Consulta> dados) {
        super(dados, new String[]{"Data", "Hora", "Comentários", "Animal", "Tratamento", "Veterinário", "Terminou"});
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Consulta item = getItem(rowIndex);

        if (item == null)
            throw new NullPointerException();

        return switch (columnIndex) {
            case 0 -> humanDateFormat(item.getData());
            case 1 -> item.getHora();
            case 2 -> item.getComentarios();
            case 3 -> item.getIdAnimal();
            case 4 -> item.getIdTratamento();
            case 5 -> item.getIdVeterinario();
            case 6 -> item.getTerminou();
            default -> throw new IndexOutOfBoundsException();
        };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Consulta item = dados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                item.setData((Calendar) aValue);
            case 1:
                item.setHora((int) aValue);
            case 2:
                item.setComentarios((String) aValue);
            case 3:
                item.setIdAnimal((int) aValue);
            case 4:
                item.setIdTratamento((int) aValue);
            case 5:
                item.setIdVeterinario((int) aValue);
            case 6:
                item.setTerminou((boolean) aValue);
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0,2 -> String.class;
            case 1,3,4,5 -> Integer.class;
            case 6 -> Boolean.class;
            default -> throw new IndexOutOfBoundsException();
        };
    }

}
