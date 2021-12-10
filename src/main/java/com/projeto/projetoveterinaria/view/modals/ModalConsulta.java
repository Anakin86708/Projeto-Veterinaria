/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.projetoveterinaria.view.modals;

import com.projeto.projetoveterinaria.controller.ModalController;
import com.projeto.projetoveterinaria.model.*;
import com.projeto.projetoveterinaria.model.DAO.VeterinarioDAO;
import com.projeto.projetoveterinaria.view.comboBoxModels.TratamentoComboBoxModel;
import com.projeto.projetoveterinaria.view.comboBoxModels.VeterinarioComboBoxModel;
import com.projeto.projetoveterinaria.view.tableModels.AnimalTableModel;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ariel
 */
public class ModalConsulta extends ModalGeneric {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnOK;
    private javax.swing.JCheckBox checkFinalizado;
    private javax.swing.JComboBox<String> cmbHorario;
    private javax.swing.JComboBox<String> cmbTratamento;
    private javax.swing.JComboBox<String> cmbVeterinario;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel panelBottom;
    private javax.swing.JTable tableAnimal;
    private javax.swing.JTextArea txtComentários;
    private javax.swing.JTextField txtID;
    /**
     * Creates new form DialogConsulta
     */
    public ModalConsulta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setCurrentDate();
        loadAnimalModel();
        loadVeterinarioModel();
        loadTratamentoModel();
        loadHorarioModel();

        createNewID();
    }
    public ModalConsulta(java.awt.Frame parent, boolean modal, Consulta data) {
        super(parent, modal);
        initComponents();
        setCurrentDate();
        loadAnimalModel();
        loadVeterinarioModel();
        loadTratamentoModel();
        loadHorarioModel();

        setupData(data);
    }

    private void setCurrentDate() {
        jDateChooser.setDate(new Date());
    }

    private void loadHorarioModel() {
        ModalController.setModelCmbHorario(cmbHorario);
    }

    private void loadTratamentoModel() {
        ModalController.setModelCmbTratamento(cmbTratamento);
    }

    private void loadVeterinarioModel() {
        ModalController.setModelCmbVeterinario(cmbVeterinario);
    }

    private void loadAnimalModel() {
        ModalController.setModelAnimais(tableAnimal);
    }

    @Override
    protected void createNewID() {
        int id = ModalController.getNewIDConsulta();
        txtID.setText(String.valueOf(id));
    }

    private void setupData(Consulta data) {
        txtID.setText(String.valueOf(data.getId()));
        txtComentários.setText(data.getComentarios());
        checkFinalizado.setSelected(data.getTerminou());

        selectRowAnimal(data);
        selectItemVeterinario(data);
        selectItemTratamento(data);
        selectHorario(data);
    }

    private Consulta getData() {
        int id = Integer.parseInt(txtID.getText());
        String comentario = txtComentários.getText();
        boolean finalizado = checkFinalizado.isSelected();
        int selecteRow = tableAnimal.getSelectedRow();


        Calendar data = jDateChooser.getCalendar();
        Animal animal = ((AnimalTableModel) tableAnimal.getModel()).getItem(selecteRow);
        Veterinario veterinario = ((VeterinarioComboBoxModel) cmbVeterinario.getModel()).getVeterinarioAt(cmbVeterinario.getSelectedIndex());
        Tratamento tratamento = ((TratamentoComboBoxModel) cmbTratamento.getModel()).getTratamentoAt(cmbTratamento.getSelectedIndex());
        Horarios horario = Horarios.valueOf((String) cmbHorario.getSelectedItem());

        return new Consulta(id, data, horario, comentario, animal.getId(), tratamento.getId(), veterinario.getId(), finalizado);
    }

    private void selectHorario(Consulta data) {
        cmbHorario.setSelectedItem(data.getHora().toString());
    }

    private void selectItemVeterinario(Consulta data) {
        int idVeterinario = data.getIdVeterinario();
        try {
            String nomeVeterinario = VeterinarioDAO.getInstance().retrieveById(idVeterinario).getNome();
            cmbVeterinario.setSelectedItem(nomeVeterinario);
        } catch (RuntimeException e) {
            feedback("Não há um veterinário para essa consulta!", "Consulta sem veterinário", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void selectItemTratamento(Consulta data) {
        int idTratamento = data.getIdTratamento();
        try {
            TratamentoComboBoxModel model = ((TratamentoComboBoxModel) cmbTratamento.getModel());
            int index = model.getRowIndexForItem(idTratamento);
            cmbTratamento.setSelectedItem(model.getElementAt(index));
        } catch (NullPointerException e) {
            feedback("Não há um tratamento para essa consulta!", "Consulta sem tratamento", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void selectRowAnimal(Consulta data) {
        int idAnimal = data.getIdAnimal();
        try {
            int indexToSelect = ((AnimalTableModel) tableAnimal.getModel()).getRowIndexForItem(idAnimal);
            tableAnimal.setRowSelectionInterval(indexToSelect, indexToSelect);
        } catch (NullPointerException e) {
            feedback("Não há um animal para essa consulta!", "Consulta sem animal", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBottom = new javax.swing.JPanel();
        btnOK = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableAnimal = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtComentários = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        cmbVeterinario = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cmbTratamento = new javax.swing.JComboBox<>();
        checkFinalizado = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbHorario = new javax.swing.JComboBox<>();
        jDateChooser = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBottomLayout = new javax.swing.GroupLayout(panelBottom);
        panelBottom.setLayout(panelBottomLayout);
        panelBottomLayout.setHorizontalGroup(
            panelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBottomLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnOK)
                .addContainerGap())
        );
        panelBottomLayout.setVerticalGroup(
            panelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBottomLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOK)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        txtID.setEditable(false);
        txtID.setText("0");
        txtID.setEnabled(false);

        jLabel1.setText("ID");

        jLabel2.setText("Animal");

        tableAnimal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableAnimal);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel3.setText("Comentários");

        txtComentários.setColumns(20);
        txtComentários.setRows(5);
        jScrollPane2.setViewportView(txtComentários);

        jLabel4.setText("Veterinário");

        cmbVeterinario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Tratamento");

        cmbTratamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        checkFinalizado.setText("Finalizado");

        jLabel6.setText("Data");

        jLabel7.setText("Horário");

        cmbHorario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(cmbVeterinario, 0, 209, Short.MAX_VALUE)
                                    .addComponent(jLabel5)
                                    .addComponent(cmbTratamento, 0, 209, Short.MAX_VALUE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(cmbHorario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(checkFinalizado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbVeterinario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbTratamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkFinalizado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        try {
            Consulta data = getData();
            String msg = ModalController.sendData(data);
            feedback(msg, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.err.println("EXCEPTION: " + e.getMessage());
            feedback("Não foi possível inserir o animal!", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (RuntimeException e) {
            System.err.println("EXCEPTION: " + e.getMessage());
            feedback("Não foi possível inserir o animal!\n"+ e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            exit();
        }
    }//GEN-LAST:event_btnOKActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        exit();
    }//GEN-LAST:event_btnCancelarActionPerformed
    // End of variables declaration//GEN-END:variables
}
