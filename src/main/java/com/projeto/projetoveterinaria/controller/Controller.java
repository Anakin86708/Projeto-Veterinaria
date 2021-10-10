package com.projeto.projetoveterinaria.controller;

import com.projeto.projetoveterinaria.model.DAO.AnimalDAO;
import com.projeto.projetoveterinaria.model.DAO.ClienteDAO;
import com.projeto.projetoveterinaria.model.DAO.ConsultaDAO;
import com.projeto.projetoveterinaria.model.DAO.ExameDAO;
import com.projeto.projetoveterinaria.model.DAO.TratamentoDAO;
import com.projeto.projetoveterinaria.model.DAO.VeterinarioDAO;
import com.projeto.projetoveterinaria.view.PanelPadrao;
import com.projeto.projetoveterinaria.view.tableModels.AnimalTableModel;
import com.projeto.projetoveterinaria.view.tableModels.ClienteTableModel;
import com.projeto.projetoveterinaria.view.tableModels.ConsultaTableModel;
import com.projeto.projetoveterinaria.view.tableModels.ExameTableModel;
import com.projeto.projetoveterinaria.view.tableModels.TratamentoTableModel;
import com.projeto.projetoveterinaria.view.tableModels.VeterinarioTableModel;

import java.awt.Component;
import javax.swing.table.TableModel;

/**
 * @author ariel
 */
public class Controller {

    private static Component instancePanelAnimal;
    private static Component instancePanelConsulta;
    private static Component instancePanelExame;
    private static Component instancePanelTratamento;
    private static Component instancePanelVeterinario;

    public static Component getPanelAnimal() {
        if (instancePanelAnimal == null) {
            instancePanelAnimal = new PanelPadrao("Animais", new AnimalTableModel(AnimalDAO.getInstance().retrieveAll()));
        }
        return instancePanelAnimal;
    }

    public static TableModel getModelCliente() {
        return new ClienteTableModel(ClienteDAO.getInstance().retrieveAll());
    }

    public static TableModel getModelProximasConsultas() {
        return new ConsultaTableModel(ConsultaDAO.getInstance().retrieveProximasConsultas());
    }

    public static TableModel getModelHistoricoConsultas() {
        return new ConsultaTableModel(ConsultaDAO.getInstance().retrieveHistoricoConsultas());
    }

    public static Component getPanelConsulta() {
        if (instancePanelConsulta == null) {
            instancePanelConsulta = new PanelPadrao("Consultas", new ConsultaTableModel(ConsultaDAO.getInstance().retrieveAll()));
        }
        return instancePanelConsulta;

    }

    public static Component getPanelExame() {
        if (instancePanelExame == null) {
            instancePanelExame = new PanelPadrao("Exames", new ExameTableModel(ExameDAO.getInstance().retrieveAll()));
        }
        return instancePanelExame;
    }

    public static Component getPanelTratamento() {
        if (instancePanelTratamento == null) {
            instancePanelTratamento = new PanelPadrao("Tratamentos", new TratamentoTableModel(TratamentoDAO.getInstance().retrieveAll()));
        }
        return instancePanelTratamento;
    }

    public static Component getPanelVeterinario() {
        if (instancePanelVeterinario == null) {
            instancePanelVeterinario = new PanelPadrao("Veterinários", new VeterinarioTableModel(VeterinarioDAO.getInstance().retrieveAll()));
        }
        return instancePanelVeterinario;
    }

}
