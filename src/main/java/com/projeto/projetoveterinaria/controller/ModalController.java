package com.projeto.projetoveterinaria.controller;

import com.projeto.projetoveterinaria.model.*;
import com.projeto.projetoveterinaria.model.DAO.*;
import com.projeto.projetoveterinaria.view.comboBoxModels.TratamentoComboBoxModel;
import com.projeto.projetoveterinaria.view.comboBoxModels.VeterinarioComboBoxModel;
import com.projeto.projetoveterinaria.view.tableModels.AnimalTableModel;
import com.projeto.projetoveterinaria.view.tableModels.ConsultaTableModel;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ModalController {

    public static String sendData(Cliente data) throws SQLException {
        // Decidir se vai ser um insert ou update
        if (data.getId() == getNewIDCliente()) {
            // Insert
            ClienteDAO.getInstance().create(data.getNome(), data.getEndereco(), data.getCep(), data.getEmail(), data.getTelefone());
            return "Cliente criado com sucesso!";
        } else {
            // Update
            ClienteDAO.getInstance().update(data);
            return "Cliente atualizado com sucesso!";
        }
    }

    public static String sendData(Animal data) throws SQLException {
        if (data.getId() == getNewIDAnimal()) {
            AnimalDAO.getInstance().create(data.getNome(), data.getAnoNasc(), data.getSexo(), data.getIdEspecie(), data.getIdCliente());
            return "Animal criado com sucesso!";
        } else {
            AnimalDAO.getInstance().update(data);
            return "Animal atualizado com sucesso!";
        }
    }

    public static String sendData(Consulta data) throws SQLException{
        if (data.getId() == getNewIDConsulta()) {
            ConsultaDAO.getInstance().create(data.getData(), data.getHora(), data.getComentarios(), data.getIdAnimal(), data.getIdTratamento(), data.getIdVeterinario(), data.getTerminou());
            return "Consulta criada com sucesso!";
        } else {
            ConsultaDAO.getInstance().update(data);
            return "Consulta atualizada com sucesso!";
        }
    }

    public static String sendData(Exame data) throws SQLException {
        if (data.getId() == getNewIDExame()) {
            ExameDAO.getInstance().create(data.getIdConsulta(), data.getDescricaoExame());
            return "Exame criado com sucesso!";
        } else {
            ExameDAO.getInstance().update(data);
            return "Exame atualizado com sucesso!";
        }
    }

    public static String sendData(Tratamento data) throws SQLException {
        if (data.getId() == getNewIDTratamento()) {
            TratamentoDAO.getInstance().create(data.getNome(), data.getDataEntrada(), data.getDataSaida(), data.getIdAnimal(), data.getTerminou());
            return "Tratamento criado com sucesso!";
        } else {
            TratamentoDAO.getInstance().update(data);
            return "Tratamento atualizado com sucesso!";
        }
    }

    public static String sendData(Veterinario data) throws SQLException {
        if (data.getId() == getNewIDVeterinario()) {
            VeterinarioDAO.getInstance().create(data.getNome(), data.getEndereco(), data.getTelefone());
            return "Veterinário criado com sucesso!";
        } else {
            VeterinarioDAO.getInstance().update(data);
            return "Veterinário atualizado com sucesso!";
        }
    }

    public static int getNewIDCliente() {
        return ClienteDAO.getInstance().getNextId();
    }

    public static int getNewIDAnimal() {
        return AnimalDAO.getInstance().getNextId();
    }

    public static int getNewIDConsulta() {
        return ConsultaDAO.getInstance().getNextId();
    }

    public static int getNewIDExame() {
        return ExameDAO.getInstance().getNextId();
    }

    public static int getNewIDTratamento() {
        return TratamentoDAO.getInstance().getNextId();
    }

    public static int getNewIDVeterinario() {
        return VeterinarioDAO.getInstance().getNextId();
    }

    /**
     * Usado para popular uma tabela com todos os animais no BD.
     *
     * @param tableAnimal tabela a ser populada.
     */
    public static void setModelAnimais(JTable tableAnimal) {
        List<Animal> animalList = AnimalDAO.getInstance().retrieveAll();
        AnimalTableModel model = new AnimalTableModel(animalList);
        tableAnimal.setModel(model);
    }

    public static void setTableModelConsultas(JTable tableConsulta) {
        List<Consulta> consultaList = ConsultaDAO.getInstance().retrieveAll();
        ConsultaTableModel model = new ConsultaTableModel(consultaList);
        tableConsulta.setModel(model);
    }

    public static void setModelCmbEspecie(JComboBox<Especie> comboBox) {
        Especie[] model = EspecieDAO.getInstance().retrieveAll().toArray(Especie[]::new);
        comboBox.setModel(new DefaultComboBoxModel<>(model));
    }

    public static void setModelCmbVeterinario(JComboBox<String> cmbVeterinario) {
        Veterinario[] dados = VeterinarioDAO.getInstance().retrieveAll().toArray(new Veterinario[0]);
        cmbVeterinario.setModel(new VeterinarioComboBoxModel(dados));
    }

    public static void setModelCmbTratamento(JComboBox<String> cmbTratamento) {
        Tratamento[] dados = TratamentoDAO.getInstance().retrieveAll().toArray(new Tratamento[0]);
        cmbTratamento.setModel(new TratamentoComboBoxModel(dados));
    }

    public static void setModelCmbHorario(JComboBox<String> cmbHorario) {
        String[] strings = Arrays.stream(Horarios.values()).map(Horarios::toString).toArray(String[]::new);
        cmbHorario.setModel(new DefaultComboBoxModel<>(strings));
    }
}
