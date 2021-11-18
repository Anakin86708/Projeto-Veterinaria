package com.projeto.projetoveterinaria.view.tableModels;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ariel
 * @param <T>
 */
public abstract class GenericTableModel<T> extends AbstractTableModel{
    protected final ArrayList<T> dados;
    protected final String[] colunas;
    protected final String nomeTabelaSQL;

    public GenericTableModel(List<T> dados, String[] colunas, String tabela) {
        this.dados = new ArrayList<>(dados);
        this.colunas = colunas;
        this.nomeTabelaSQL = tabela;
    }

    public String getNomeTabelaSQL() {
        return nomeTabelaSQL;
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    public String[] getColunas() {
        return colunas;
    }

    // Metodos auxiliares:
    public T getItem(int indiceLinha) {
        if (indiceLinha < 0) {
            return null;
        }
        return dados.get(indiceLinha);
    }

    public void addItem(T obj) {
        dados.add(obj);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void removeItem(int indiceLinha) {
        dados.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    public void addListOfItems(List<T> vItens) {
        this.clear();
        for (T obj : vItens){
            this.addItem(obj);
        }
    }

    public void clear() {
        dados.clear();
        fireTableDataChanged();
    }

    public boolean isEmpty() {
        return dados.isEmpty();
    }

    protected String humanDateFormat(Calendar data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(new Date(data.getTimeInMillis()));
    }
}
