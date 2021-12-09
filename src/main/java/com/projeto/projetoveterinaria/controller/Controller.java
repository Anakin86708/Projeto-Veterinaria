package com.projeto.projetoveterinaria.controller;

import com.projeto.projetoveterinaria.controller.modal.*;
import com.projeto.projetoveterinaria.model.*;
import com.projeto.projetoveterinaria.model.DAO.*;
import com.projeto.projetoveterinaria.view.PanelPadrao;
import com.projeto.projetoveterinaria.view.modals.ModalCliente;
import com.projeto.projetoveterinaria.view.tableModels.*;

import javax.swing.*;
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

    public static void setTableModelCliente(JTable table) {
        ClienteTableModel model = new ClienteTableModel(ClienteDAO.getInstance().retrieveAll());
        table.setModel(model);
    }

    public static void setTableModelProximasConsultas(JTable table) {
        ConsultaTableModel model = new ConsultaTableModel(ConsultaDAO.getInstance().retrieveProximasConsultas());
        table.setModel(model);
    }

    public static void setTableModelHistoricoConsultas(JTable table) {
        ConsultaTableModel model = new ConsultaTableModel(ConsultaDAO.getInstance().retrieveHistoricoConsultas());
        table.setModel(model);
    }

    public static void setTableDefaultModelAnimais(JTable table) {
        AnimalTableModel model = new AnimalTableModel();
        table.setModel(model);
    }

    public static void editSelectedClient(JFrame parent, int selected, JTable tableCliente) throws IllegalArgumentException {
        validateSelect(selected);
        Cliente item = ((ClienteTableModel) tableCliente.getModel()).getItem(selected);
        JDialog frame = new ModalCliente(parent, true, item);
        frame.setVisible(true);
    }

    public static void removeSelectedClient(JFrame parent, int selected, JTable tableCliente) throws IllegalArgumentException{
        validateSelect(selected);
        Cliente item = ((ClienteTableModel) tableCliente.getModel()).getItem(selected);

        int confirmDialog = JOptionPane.showConfirmDialog(parent, "Deseja remover (" + item.getId() + ") " + item.getNome() + " permanentemente?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirmDialog == JOptionPane.YES_OPTION) {
            ClienteDAO.getInstance().delete(item);
        }
    }

    public static void validateSelect(int selected) throws IllegalArgumentException{
        if (selected == -1) {
            throw new IllegalArgumentException("Selecione um item válido.");
        }
    }

    public static void setComboModelFiltroCliente(JComboBox<String> cmbFiltroCliente) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(ClienteTableModel.nomeColunas);
        cmbFiltroCliente.setModel(model);
    }

    public Component getPanelAnimal() {
        if (instancePanelAnimal == null) {
            final AnimalTableModel animalTableModel = new AnimalTableModel(AnimalDAO.getInstance().retrieveAll());
            final IModalController controller = new ModalControllerAnimal(frameAssociado);
            instancePanelAnimal = new PanelPadrao<Animal>("Animais", controller);
        }
        return instancePanelAnimal;
    }

    public Component getPanelConsulta() {
        if (instancePanelConsulta == null) {
            final ConsultaTableModel consultaTableModel = new ConsultaTableModel(ConsultaDAO.getInstance().retrieveAll());
            final IModalController controller = new ModalControllerConsulta(frameAssociado);
            instancePanelConsulta = new PanelPadrao<Consulta>("Consultas", controller);
        }
        return instancePanelConsulta;

    }

    public Component getPanelExame() {
        if (instancePanelExame == null) {
            final ExameTableModel exameTableModel = new ExameTableModel(ExameDAO.getInstance().retrieveAll());
            final IModalController controller = new ModalControllerExame(frameAssociado);
            instancePanelExame = new PanelPadrao<Exame>("Exames", controller);
        }
        return instancePanelExame;
    }

    public Component getPanelTratamento() {
        if (instancePanelTratamento == null) {
            final TratamentoTableModel tratamentoTableModel = new TratamentoTableModel(TratamentoDAO.getInstance().retrieveAll());
            final IModalController controller = new ModalControllerTratamento(frameAssociado);
            instancePanelTratamento = new PanelPadrao<Tratamento>("Tratamentos", controller);
        }
        return instancePanelTratamento;
    }

    public Component getPanelVeterinario() {
        if (instancePanelVeterinario == null) {
            final VeterinarioTableModel veterinarioTableModel = new VeterinarioTableModel(VeterinarioDAO.getInstance().retrieveAll());
            final IModalController controller = new ModalControllerVeterinario(frameAssociado);
            instancePanelVeterinario = new PanelPadrao<Veterinario>("Veterinários", controller);
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
