package com.projeto.projetoveterinaria.controller.listenners;

import com.projeto.projetoveterinaria.controller.Controller;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 * Este TableModelListener é responsável por atualizar os dados na tabela
 * na página inicial, quando consultas são modificadas.
 */
public class TableListenner implements TableModelListener {

    private final JTable tableProximasConsultas;
    private final JTable tableHistorico;

    public TableListenner(JTable tableProximasConsultas, JTable tableHistorico) {
        this.tableProximasConsultas = tableProximasConsultas;
        this.tableHistorico = tableHistorico;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        System.out.println("Table change disparado!");
        Controller.setTableModelProximasConsultas(tableProximasConsultas);
        Controller.setTableModelHistoricoConsultas(tableHistorico);
    }
}
