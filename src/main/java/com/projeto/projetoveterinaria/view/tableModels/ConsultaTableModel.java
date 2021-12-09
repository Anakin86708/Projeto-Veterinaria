package com.projeto.projetoveterinaria.view.tableModels;

import com.projeto.projetoveterinaria.model.Consulta;
import com.projeto.projetoveterinaria.model.DAO.AnimalDAO;
import com.projeto.projetoveterinaria.model.DAO.TratamentoDAO;
import com.projeto.projetoveterinaria.model.DAO.VeterinarioDAO;
import com.projeto.projetoveterinaria.model.Horarios;

import java.util.Calendar;
import java.util.List;

/**
 * @author ariel
 */
public class ConsultaTableModel extends GenericTableModel<Consulta> {

    private static final String[] nomeColunas = {"Data", "Hora", "Comentários", "Animal", "Tratamento", "Veterinário", "Terminou"};
    private static final String nomeTabelaSQL = "consulta";


    public ConsultaTableModel(List<Consulta> dados) {
        super(dados, nomeColunas, nomeTabelaSQL);
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
            case 3 -> getNomeAnimal(item.getIdAnimal());
            case 4 -> getNomeTratamento(item.getIdTratamento());
            case 5 -> getNomeVeterinario(item.getIdVeterinario());
            case 6 -> item.getTerminou();
            default -> throw new IndexOutOfBoundsException();
        };
    }

    private String getNomeAnimal(int idAnimal) {
        try {
            return AnimalDAO.getInstance().retrieveById(idAnimal).getNome();
        } catch (RuntimeException e) {
            return "ANIMAL REMOVIDO";
        }
    }

    private String getNomeTratamento(int idTratamento) {
        try {
            return TratamentoDAO.getInstance().retrieveById(idTratamento).getNome();
        } catch (Exception e) {
            return "TRATAMENTO REMOVIDO";
        }
    }

    private String getNomeVeterinario(int idVeterinario) {
        try {
            return VeterinarioDAO.getInstance().retrieveById(idVeterinario).getNome();
        } catch (Exception e) {
            return "VETERINÁRIO REMOVIDO";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Consulta item = dados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                item.setData((Calendar) aValue);
            case 1:
                item.setHora(Horarios.valueOf((String) aValue));
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
            case 0, 1, 2, 3, 4, 5 -> String.class;
            case 6 -> Boolean.class;
            default -> throw new IndexOutOfBoundsException();
        };
    }

}
