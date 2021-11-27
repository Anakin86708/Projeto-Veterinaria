package com.projeto.projetoveterinaria.controller;

import com.projeto.projetoveterinaria.model.Cliente;
import com.projeto.projetoveterinaria.model.DAO.*;
import com.projeto.projetoveterinaria.view.PanelPadrao;
import com.projeto.projetoveterinaria.view.modals.*;
import com.projeto.projetoveterinaria.view.tableModels.*;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

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

    public static void editSelectedClient(JFrame parent, int selected, JTable tableCliente) throws IllegalArgumentException {
        if (selected == -1){
            throw new IllegalArgumentException("Selecione um item válido.");
        }
        Cliente item = ((ClienteTableModel) tableCliente.getModel()).getItem(selected);
        JDialog frame = new ModalCliente(parent, true, item);
        frame.setVisible(true);
    }

    public Component getPanelAnimal() {
        if (instancePanelAnimal == null) {
            final AnimalTableModel animalTableModel = new AnimalTableModel(AnimalDAO.getInstance().retrieveAll());
            final ModalAnimal modalAnimal = new ModalAnimal(frameAssociado, true);
            instancePanelAnimal = new PanelPadrao("Animais", animalTableModel, modalAnimal);
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

    public static void searchFor(JTextField txtBusca, JComboBox<String> cmbFiltro, JTable tableConteudo) {
        String textoBuscado = txtBusca.getText();
        String valorCmb = (String) cmbFiltro.getSelectedItem();
        String nomeTabela = ((GenericTableModel) tableConteudo.getModel()).getNomeTabelaSQL();
        String nomeColunaSQL;

        switch (nomeTabela) {
            case "animal" -> {
                nomeColunaSQL = getSelectedColAnimal(valorCmb);
                tableConteudo.setModel(new AnimalTableModel(
                        AnimalDAO.getInstance().retrieveBySimilarValueOnColumn(textoBuscado, nomeColunaSQL)
                ));
            }
            case "cliente" -> {
                nomeColunaSQL = getSelectedColCliente(valorCmb);
                tableConteudo.setModel(new ClienteTableModel(
                        ClienteDAO.getInstance().retrieveBySimilarValueOnColumn(textoBuscado, nomeColunaSQL)
                ));
            }
            case "vet" -> {
                nomeColunaSQL = getSelectedColVet(valorCmb);
                tableConteudo.setModel(new VeterinarioTableModel(
                        VeterinarioDAO.getInstance().retrieveBySimilarValueOnColumn(textoBuscado, nomeColunaSQL)
                ));
            }
            case "tratamento" -> {
                nomeColunaSQL = getSelectedColTratamento(valorCmb);
                tableConteudo.setModel(new TratamentoTableModel(
                        TratamentoDAO.getInstance().retrieveBySimilarValueOnColumn(textoBuscado, nomeColunaSQL)
                ));
            }
            case "consulta" -> {
                nomeColunaSQL = getSelectedColConsulte(valorCmb);
                tableConteudo.setModel(new ConsultaTableModel(
                        ConsultaDAO.getInstance().retrieveBySimilarValueOnColumn(textoBuscado, nomeColunaSQL)
                ));
            }
            case "exame" -> {
                nomeColunaSQL = getSelectedColExame(valorCmb);
                tableConteudo.setModel(new ExameTableModel(
                        ExameDAO.getInstance().retrieveBySimilarValueOnColumn(textoBuscado, nomeColunaSQL)
                ));
            }
        }
    }

    private static String getSelectedColExame(String valorCmb) {
        return switch (valorCmb) {
            case "Consulta" -> "id_consulta";
            default -> "decricao_exame";
        };
    }

    private static String getSelectedColConsulte(String valorCmb) {
        return switch (valorCmb) {
            case "Data" -> "data";
            case "Hora" -> "horario";
            case "Animal" -> "id_animal";
            case "Tratamento" -> "id_tratamento";
            case "Veterinário" -> "id_vet";
            case "Terminou" -> "terminado";
            default -> "comentario";
        };
    }

    private static String getSelectedColTratamento(String valorCmb) {
        return switch (valorCmb) {
            case "Data entrada" -> "dataIni";
            case "Data saída" -> "dataFim";
            case "Animal" -> "id_animal";
            case "Terminou" -> "terminado";
            default -> "nome";
        };
    }

    private static String getSelectedColVet(String valorCmb) {
        return switch (valorCmb) {
            case "Endereço" -> "endereco";
            case "Telefone" -> "telefone";
            default -> "nome";
        };
    }

    private static String getSelectedColCliente(String valorCmb) {
        return switch (valorCmb) {
            case "Endereço" -> "end";
            case "Telefone" -> "telefone";
            case "cep" -> "cep";
            case "Email" -> "email";
            default -> "nome";
        };
    }

    private static String getSelectedColAnimal(String valorCmb) {
        return switch (valorCmb) {
            case "Ano de nascimento" -> "anoNasc";
            case "Sexo" -> "sexo";
            case "ID Espécie" -> "id_especie";
            case "ID Cliente" -> "id_cliente";
            default -> "nome";
        };
    }


}
