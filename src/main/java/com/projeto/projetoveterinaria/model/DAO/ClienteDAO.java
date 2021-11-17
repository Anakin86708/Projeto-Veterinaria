package com.projeto.projetoveterinaria.model.DAO;

import com.projeto.projetoveterinaria.model.Cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ariel
 */
public class ClienteDAO extends DAO<Cliente> {

    private static ClienteDAO instance;
    public final static String COLUMN_NAME = "cliente";


    private ClienteDAO() {
        super(COLUMN_NAME);
        getConnection();
        createTable();
    }

    public static ClienteDAO getInstance() {
        return (instance == null ? (instance = new ClienteDAO()) : instance);
    }


    // CRUD
    public Cliente create(String nome, String endereco, String cep, String email, String telefone) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("INSERT INTO cliente (nome, end, cep, email, telefone) VALUES (?,?,?,?,?)");
            stmt.setString(1, nome);
            stmt.setString(2, endereco);
            stmt.setString(3, cep);
            stmt.setString(4, email);
            stmt.setString(5, telefone);
            this.executeUpdate(stmt);
        } catch (Exception ex) {
            System.err.println("EXCEPTION: " + ex.getMessage());
        }
        return retrieveLast();
    }

    public List<Cliente> retrieveAll() {
        //language=SQL
        String query = "SELECT * FROM cliente";
        return retrieve(query);
    }

    public Cliente retrieveById(int id) {
        //language=SQL
        String query = "SELECT * FROM cliente WHERE id = " + id;
        List<Cliente> client = retrieve(query);
        if (client.isEmpty()) {
            throw new RuntimeException("Nenhum cliente encontrado com id " + id);
        }
        return client.get(0);
    }

    public Cliente retrieveLast() {
        //language=SQL
        String query = "SELECT * FROM cliente WHERE id = (SELECT max(id) FROM cliente)";
        List<Cliente> client = retrieve(query);
        return client.get(0);
    }

    public List<Cliente> retrieveBySimilarName(String nome) {
        //language=SQL
        String query = "SELECT * FROM cliente WHERE nome LIKE '%" + nome + "%'";
        return retrieve(query);
    }

    protected Cliente buildObject(ResultSet rs) throws SQLException {
        return new Cliente(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("end"),
                rs.getString("telefone"),
                rs.getString("cep"),
                rs.getString("email")

        );
    }

    public void update(Cliente client) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("UPDATE cliente SET nome=?, end=?, cep=?, email=?, telefone=? WHERE id=?");
            stmt.setString(1, client.getNome());
            stmt.setString(2, client.getEndereco());
            stmt.setString(3, client.getCep());
            stmt.setString(4, client.getEmail());
            stmt.setString(5, client.getTelefone());
            stmt.setInt(6, client.getId());
            this.executeUpdate(stmt);
        } catch (SQLException ex) {
            System.err.println("EXCEPTION: " + ex.getMessage());
        }

    }

    public void delete(Cliente client) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("DELETE FROM cliente WHERE id = ?");
            stmt.setInt(1, client.getId());
            this.executeUpdate(stmt);
        } catch (SQLException ex) {
            System.err.println("EXCEPTION: " + ex.getMessage());
        }
    }

}
