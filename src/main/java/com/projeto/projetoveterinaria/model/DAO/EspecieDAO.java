package com.projeto.projetoveterinaria.model.DAO;

import com.projeto.projetoveterinaria.model.Especie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EspecieDAO extends DAO<Especie> {

    private static EspecieDAO instance;

    private EspecieDAO() {
        getConnection();
        createTable();
    }

    public static EspecieDAO getInstance() {
        return (instance == null ? (instance = new EspecieDAO()) : instance);
    }

    @Override
    protected Especie buildObject(ResultSet rs) throws SQLException {
        return new Especie(
                rs.getInt("id"),
                rs.getString("nome")
        );
    }

    public Especie create(String nome) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("INSERT INTO especie (nome) VALUES (?)");
            stmt.setString(1, nome);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            System.err.println("EXCEPTION: " + ex.getMessage());
        }
        return retrieveLast();
    }

    public Especie retrieveLast() {
        //language=SQL
        String quary = "SELECT * FROM especie WHERE id = (SELECT max(id) FROM especie)";
        List<Especie> especie = retrieve(quary);
        return especie.get(0);
    }

    public List<Especie> retrieveAll() {
        //language=SQL
        String quary = "SELECT * FROM especie";
        return retrieve(quary);
    }

    public Especie retrieveById(int id) {
        //language=SQL
        String query = "SELECT * FROM cliente WHERE id = " + id;
        List<Especie> especie = retrieve(query);
        if (especie.isEmpty()) {
            throw new RuntimeException("Nenhuma especie encontrada com id " + id);
        }
        return especie.get(0);
    }

    public List<Especie> retrieveBySimilarName(String nome) {
        //language=SQL
        String query = "SELECT * FROM especie WHERE nome LIKE '%" + nome + "%'";
        return retrieve(query);
    }

    public void update(Especie especie) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("UPDATE especie SET nome=? WHERE  id=?");
            stmt.setString(1, especie.getNomeEspecie());
            stmt.setInt(2, especie.getId());
            executeUpdate(stmt);
        } catch (SQLException ex) {
            System.err.println("EXCEPTION: " + ex.getMessage());
        }
    }

    public void delete(Especie especie) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("DELETE FROM especie WHERE id=?");
            stmt.setInt(1, especie.getId());
            executeUpdate(stmt);
        } catch (SQLException ex) {
            System.err.println("EXCEPTION: " + ex.getMessage());
        }
    }
}
