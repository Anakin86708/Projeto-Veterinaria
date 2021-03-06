package com.projeto.projetoveterinaria.model.DAO;

import com.projeto.projetoveterinaria.model.Veterinario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VeterinarioDAO extends DAO<Veterinario> {

    private static VeterinarioDAO instance;
    public final static String TABLE_NAME = "vet";


    private VeterinarioDAO() {
        super(TABLE_NAME);
        getConnection();
        createTable();
    }

    public static VeterinarioDAO getInstance() {
        return (instance == null ? (instance = new VeterinarioDAO()) : instance);
    }

    @Override
    protected Veterinario buildObject(ResultSet rs) throws SQLException {
        return new Veterinario(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("endereco"),
                rs.getString("telefone")
        );
    }

    protected String createQueryWithFK(String value, String column) {
        return getQuery(value, column);
    }

    public Veterinario create(String nome, String endereco, String telefone) throws SQLException {
        PreparedStatement stmt = DAO.getConnection().prepareStatement("INSERT INTO vet (nome, endereco, telefone) VALUES (?,?,?)");
        stmt.setString(1, nome);
        stmt.setString(2, endereco);
        stmt.setString(3, telefone);
        executeUpdate(stmt);
        return retrieveLast();
    }


    public List<Veterinario> retrieveAll() {
        //language=SQL
        String query = "SELECT * FROM vet";
        return retrieve(query);
    }

    public Veterinario retrieveLast() {
        //language=SQL
        String query = "SELECT * FROM vet WHERE id = (SELECT max(id) FROM vet)";
        List<Veterinario> vet = retrieve(query);
        return vet.get(0);
    }

    public Veterinario retrieveById(int id) {
        //language=SQL
        String query = "SELECT * FROM vet WHERE id = " + id;
        List<Veterinario> vet = retrieve(query);
        if (vet.isEmpty()) {
            throw new RuntimeException("Nenhum veterin??rio encontrado com id " + id);
        }
        return vet.get(0);
    }

    public List<Veterinario> retrieveBySimilarName(String nome) {
        //language=SQL
        String query = "SELECT * FROM vet WHERE nome LIKE '%" + nome + "%'";
        return retrieve(query);
    }

    public void update(Veterinario vet) throws SQLException {
        PreparedStatement stmt = DAO.getConnection().prepareStatement("UPDATE vet SET nome=?, endereco=?, telefone=? WHERE id=?");
        stmt.setString(1, vet.getNome());
        stmt.setString(2, vet.getEndereco());
        stmt.setString(3, vet.getTelefone());
        stmt.setInt(4, vet.getId());
        executeUpdate(stmt);
    }

    public void delete(Veterinario vet) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("DELETE FROM vet WHERE id=?");
            stmt.setInt(1, vet.getId());
            executeUpdate(stmt);
        } catch (SQLException ex) {
            System.err.println("EXCEPTION: " + ex.getMessage());
        }
    }

    public int getNextId() {
        return nextId(TABLE_NAME);
    }
}
