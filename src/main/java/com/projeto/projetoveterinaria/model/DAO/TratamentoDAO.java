package com.projeto.projetoveterinaria.model.DAO;

import com.projeto.projetoveterinaria.model.Tratamento;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Calendar;
import java.util.List;

public class TratamentoDAO extends DAO<Tratamento> {

    private static TratamentoDAO instance;

    private TratamentoDAO() {
        getConnection();
        createTable();
    }

    public static TratamentoDAO getInstance() {
        return (instance == null ? (instance = new TratamentoDAO()) : instance);
    }

    @Override
    protected Tratamento buildObject(ResultSet rs) throws SQLException {
        Calendar dataEntrada = Calendar.getInstance();
        dataEntrada.setTime(Date.from(Instant.ofEpochMilli(rs.getLong("dataIni"))));

        Calendar dataSaida = Calendar.getInstance();
        dataSaida.setTime(Date.from(Instant.ofEpochMilli(rs.getLong("dataFim"))));
        return new Tratamento(
                rs.getInt("id"),
                rs.getString("nome"),
                dataEntrada,
                dataSaida,
                rs.getInt("id_animal"),
                rs.getBoolean("terminado")
        );
    }

    public Tratamento create(String nome, Calendar dataEntrada, Calendar dataSaida, int idAnimal, boolean terminou) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("INSERT INTO tratamento (nome, dataIni, dataFim, id_animal, terminado) VALUES  (?,?,?,?,?)");
            stmt.setString(1, nome);
            stmt.setLong(2, dataEntrada.getTimeInMillis());
            stmt.setLong(3, dataSaida.getTimeInMillis());
            stmt.setInt(4, idAnimal);
            stmt.setBoolean(5, terminou);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            System.err.println("EXCEPTION: " + ex.getMessage());
        }
        return retrieveLast();
    }

    public Tratamento retrieveLast() {
        //language=SQL
        String query = "SELECT * FROM tratamento WHERE id = (SELECT max(id) FROM tratamento)";
        List<Tratamento> tratamento = retrieve(query);
        return tratamento.get(0);
    }

    public List<Tratamento> retrieveAll() {
        //language=SQL
        String quary = "SELECT * FROM tratamento";
        return retrieve(quary);
    }

    public Tratamento retrieveById(int id) {
        //language=SQL
        String quary = "SELECT * FROM tratamento WHERE id = " + id;
        List<Tratamento> tratamento = retrieve(quary);
        return tratamento.get(0);
    }

    public List<Tratamento> retrieveByIdAnimal(int idAnimal) {
        //language=SQL
        String quary = "SELECT * FROM tratamento WHERE id_animal = " + idAnimal;
        return retrieve(quary);
    }

    public List<Tratamento> retrieveBySimilarName(String nome) {
        //language=SQL
        String query = "SELECT * FROM tratamento WHERE nome LIKE '%" + nome + "%'";
        return retrieve(query);
    }

    public void update(Tratamento tratamento) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("UPDATE tratamento SET nome=?, dataIni=?, dataFim=?,id_animal=?, terminado=? WHERE id=?");
            stmt.setString(1, tratamento.getNome());
            stmt.setDate(2, new Date(tratamento.getDataEntrada().getTimeInMillis()));
            stmt.setDate(3, new Date(tratamento.getDataSaida().getTimeInMillis()));
            stmt.setInt(4, tratamento.getIdAnimal());
            stmt.setBoolean(5, tratamento.isTerminou());
            stmt.setInt(6, tratamento.getId());
            executeUpdate(stmt);
        } catch (SQLException ex) {
            System.err.println("EXCEPTION: " + ex.getMessage());
        }
    }

    public void delete(Tratamento tratamento) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("DELETE FROM  tratamento WHERE id = ?");
            stmt.setInt(1, tratamento.getId());
            executeUpdate(stmt);
        } catch (SQLException ex) {
            System.err.println("EXCEPTION: " + ex.getMessage());
        }
    }

}
