package com.projeto.projetoveterinaria.view.comboBoxModels;

import com.projeto.projetoveterinaria.model.Tratamento;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

public class TratamentoComboBoxModel extends AbstractListModel<String> implements ComboBoxModel<String> {
    private final Tratamento[] dados;
    private String selected;
    private final HashMap<Integer, Integer> idToPosition;

    public TratamentoComboBoxModel(Tratamento[] dados) {
        this.dados = dados;
        idToPosition = prepareHashMap();
    }

    /**
     * Itera por todos os dados, salvando o id e a posição correspondente.
     *
     * @return Mapa para facilitar a busca de index por id.
     */
    private HashMap<Integer, Integer> prepareHashMap() {
        final HashMap<Integer, Integer> idToPosition;
        idToPosition = new HashMap<>();
        List<Tratamento> dados = List.of(this.dados);

        ListIterator<Tratamento> iterator = dados.listIterator();
        while (iterator.hasNext()) {
            int i = iterator.nextIndex();
            idToPosition.put(iterator.next().getId(), i);
        }
        return idToPosition;
    }

    /**
     * Retorna o índice que expectedID se encontra na tabela.
     *
     * @param expectedID Índice do objeto buscado.
     * @return Índice para o valor desejado.
     * @throws NullPointerException Caso não exista o expectedID nos dados.
     */
    public int getRowIndexForItem(int expectedID) throws NullPointerException{
        return idToPosition.get(expectedID);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selected = (String) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selected;
    }

    @Override
    public int getSize() {
        return dados.length;
    }

    @Override
    public String getElementAt(int index) {
        return dados[index].getNome();
    }

    public Tratamento getTratamentoAt(int index) {
        return dados[index];
    }
}
