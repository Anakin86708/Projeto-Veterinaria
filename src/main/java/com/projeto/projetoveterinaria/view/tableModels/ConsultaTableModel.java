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

        switch (columnIndex) {
            case 0:
                return humanDateFormat(item.getData());
            case 1:
                return item.getHora();
            case 2:
                return item.getComentarios();
            case 3:
                return item.getIdAnimal();
            case 4:
                return item.getIdTratamento();
            case 5:
                return item.getIdVeterinario();
            case 6:
                return item.getTerminou();
            default:
                throw new IndexOutOfBoundsException();
        }
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
        switch (columnIndex) {
            case 0:
            case 2:
                return String.class;
            case 1:
            case 3:
            case 4:
            case 5:
                return int.class;
            case 6:
                return Boolean.class;
        }
        throw new IndexOutOfBoundsException();
    }

}
