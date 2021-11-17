package com.projeto.projetoveterinaria.controller;

import com.projeto.projetoveterinaria.model.Cliente;
import com.projeto.projetoveterinaria.model.DAO.AnimalDAO;
import com.projeto.projetoveterinaria.model.DAO.ClienteDAO;
import com.projeto.projetoveterinaria.model.DAO.ConsultaDAO;
import com.projeto.projetoveterinaria.model.DAO.ExameDAO;
import com.projeto.projetoveterinaria.model.DAO.TratamentoDAO;
import com.projeto.projetoveterinaria.model.DAO.VeterinarioDAO;
import com.projeto.projetoveterinaria.view.FormMain;
import com.projeto.projetoveterinaria.view.PanelPadrao;
import com.projeto.projetoveterinaria.view.modals.ModalAnimal;
import com.projeto.projetoveterinaria.view.modals.ModalConsulta;
import com.projeto.projetoveterinaria.view.modals.ModalExame;
import com.projeto.projetoveterinaria.view.modals.ModalTratamento;
import com.projeto.projetoveterinaria.view.modals.ModalVeterinario;
import com.projeto.projetoveterinaria.view.tableModels.AnimalTableModel;
import com.projeto.projetoveterinaria.view.tableModels.ClienteTableModel;
import com.projeto.projetoveterinaria.view.tableModels.ConsultaTableModel;
import com.projeto.projetoveterinaria.view.tableModels.ExameTableModel;
import com.projeto.projetoveterinaria.view.tableModels.TratamentoTableModel;
import com.projeto.projetoveterinaria.view.tableModels.VeterinarioTableModel;

import java.awt.Component;
import java.awt.Frame;
import java.util.List;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

/**
 * @author ariel
 */
public class Controller {

    private final Frame frameAssociado;
    private static Component instancePanelAnimal;
    private static Component instancePanelConsulta;
    private static Component instancePanelExame;
    private static Component instancePanelTratamento;
    private static Component instancePanelVeterinario;

    public Controller(Frame frameAssociado) {
        this.frameAssociado = frameAssociado;
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

    public static TableModel getDefaultModelAnimais() {
        return new AnimalTableModel();
    }

    public Component getPanelAnimal() {
        if (instancePanelAnimal == null) {
            final AnimalTableModel animalTableModel = new AnimalTableModel(AnimalDAO.getInstance().retrieveAll());
            final ModalAnimal modalAnimal = new ModalAnimal(frameAssociado, true);
            instancePanelAnimal = new PanelPadrao("Animais", animalTableModel, modalAnimal);;
        }
        return instancePanelAnimal;
    }

    public Component getPanelConsulta() {
        if (instancePanelConsulta == null) {
            final ConsultaTableModel consultaTableModel = new ConsultaTableModel(ConsultaDAO.getInstance().retrieveAll());
            final ModalConsulta modalConsulta = new ModalConsulta(frameAssociado, true);
            instancePanelConsulta = new PanelPadrao("Consultas", consultaTableModel, modalConsulta);
        }
        return instancePanelConsulta;

    }

    public Component getPanelExame() {
        if (instancePanelExame == null) {
            final ExameTableModel exameTableModel = new ExameTableModel(ExameDAO.getInstance().retrieveAll());
            final ModalExame modalExame = new ModalExame(frameAssociado, true);
            instancePanelExame = new PanelPadrao("Exames", exameTableModel, modalExame);
        }
        return instancePanelExame;
    }

    public Component getPanelTratamento() {
        if (instancePanelTratamento == null) {
            final TratamentoTableModel tratamentoTableModel = new TratamentoTableModel(TratamentoDAO.getInstance().retrieveAll());
            final ModalTratamento modalTratamento = new ModalTratamento(frameAssociado, true);
            instancePanelTratamento = new PanelPadrao("Tratamentos", tratamentoTableModel, modalTratamento);
        }
        return instancePanelTratamento;
    }

    public Component getPanelVeterinario() {
        if (instancePanelVeterinario == null) {
            final VeterinarioTableModel veterinarioTableModel = new VeterinarioTableModel(VeterinarioDAO.getInstance().retrieveAll());
            final ModalVeterinario modalVeterinario = new ModalVeterinario(frameAssociado, true);
            instancePanelVeterinario = new PanelPadrao("Veterinários", veterinarioTableModel, modalVeterinario);
        }
        return instancePanelVeterinario;
    }

    public static void setSelectedCliente(JTable tableAnimaisPertencentes, Cliente item) {
        tableAnimaisPertencentes.setModel(new AnimalTableModel(AnimalDAO.getInstance().retriveByOwnerID(item.getId())));
    }

    public void initPanels(JTabbedPane tabbedPanedPane1) {
        tabbedPanedPane1.add("Animais", this.getPanelAnimal());
        tabbedPanedPane1.add("Consultas", this.getPanelConsulta());
        tabbedPanedPane1.add("Exames", this.getPanelExame());
        tabbedPanedPane1.add("Tratamentos", this.getPanelTratamento());
        tabbedPanedPane1.add("Veterinarios", this.getPanelVeterinario());
    }
    
    public static void buscarCliente(JTextField textField, JTable table) {
        String textoBuscdo = textField.getText();
        List<Cliente> dados = ClienteDAO.getInstance().retrieveBySimilarName(textoBuscdo);
        table.setModel(new ClienteTableModel(dados));
    }

}
