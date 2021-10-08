package com.projeto.projetoveterinaria.view.tableModels;

import com.projeto.projetoveterinaria.model.Animal;
import com.projeto.projetoveterinaria.model.Cliente;
import com.projeto.projetoveterinaria.model.Sexo;
import java.util.List;

/**
 *
 * @author ariel
 */
public class AnimalTableModel extends GenericTableModel<Animal>{

    public AnimalTableModel(List<Animal> dados) {
        super(dados, new String[] {"Nome", "Ano de nascimento", "Sexo", "ID Espécie", "ID Cliente"});
    }

        @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
            try {
                Animal linha = dados.get(rowIndex);
                System.out.println(linha.getSexo());
                
                if (linha == null) {
                    throw new NullPointerException();
                }
                
                switch (columnIndex) {
                    case 0:
                        return linha.getNome();
                    case 1:
                        return linha.getAnoNasc();
                    case 2:
                        return linha.getSexo();
                    case 3:
                        return linha.getIdEspecie();
                    case 4:
                        return linha.getIdCliente();
                    default:
                        throw new IndexOutOfBoundsException();
                }
            } catch (Exception exception) {
                System.err.println("Deu ruim");
                return null;
            }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Animal linha = dados.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                linha.setNome((String) aValue);
            case 1:
                linha.setAnoNasc((int) aValue);
            case 2:
                linha.setSexo((Sexo) aValue);
            case 3:
                linha.setIdEspecie((int) aValue);
            case 4:
                linha.setIdCliente((int) aValue);
            default:
                throw new IndexOutOfBoundsException();
        }
    }
    
    

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex >= 0 && columnIndex <= 4)
            return String.class;
        throw new IndexOutOfBoundsException();
    }
    
}
