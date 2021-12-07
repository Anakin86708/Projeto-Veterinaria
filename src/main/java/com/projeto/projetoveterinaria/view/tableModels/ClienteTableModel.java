package com.projeto.projetoveterinaria.view.tableModels;

import com.projeto.projetoveterinaria.model.Cliente;

import java.util.List;

/**
 *
 * @author ariel
 */
public class ClienteTableModel extends GenericTableModel<Cliente>{

    public static final String[] nomeColunas = {"Nome", "Endereço", "Telefone", "CEP", "Email"};
    public static final String nomeTabelaSQL = "cliente";


    public ClienteTableModel(List<Cliente> dados) {
        super(dados, nomeColunas, nomeTabelaSQL);
    }    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente item = getItem(rowIndex);

        if (item == null)
            throw new NullPointerException();

        return switch (columnIndex) {
            case 0 -> item.getNome();
            case 1 -> item.getEndereco();
            case 2 -> item.getTelefone();
            case 3 -> item.getCep();
            case 4 -> item.getEmail();
            default -> throw new IndexOutOfBoundsException();
        };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Cliente linha = dados.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                linha.setNome((String) aValue);
            case 1:
                linha.setEndereco((String) aValue);
            case 2:
                linha.setTelefone((String) aValue);
            case 3:
                linha.setCep((String) aValue);
            case 4:
                linha.setEmail((String) aValue);
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
