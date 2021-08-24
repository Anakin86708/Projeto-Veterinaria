package com.projeto.projetoveterinaria.model.DAO;

import com.projeto.projetoveterinaria.model.Animal;
import com.projeto.projetoveterinaria.model.Cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AnimalDAO extends DAO<Animal> {

    private static AnimalDAO instance;

    private AnimalDAO() {
        getConnection();
        createTable();
    }

    public static AnimalDAO getInstance() {return (instance == null ? (instance = new AnimalDAO()) : instance);}

    public void create(String nome, int anoNasc, int sexo) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("INSERT INTO animal (nome, anoNasc, sexo) VALUES (?,?,?)");
            stmt.setString(1, nome);
            stmt.setInt(2, anoNasc);
            stmt.setInt(3, sexo);
            this.executeUpdate(stmt);
        } catch (SQLException ex) {
            System.err.println("EXCEPTION: " + ex.getMessage());
        }
    }

    public List<Animal> retrieveAll() {
        String query = "SELECT * FROM animal";
        return retrieve(query);
    }

    public Animal retrieveById(int id) {
        String query = "SELECT * FROM animal WHERE id = " + id;
        List<Animal> client = retrieve(query);
        if (client.isEmpty()) {
            throw new RuntimeException("Nenhum animal encontrado com id " + id);
        }
        return client.get(0);
    }

    public List<Animal> retrieveBySimilarName(String nome) {
        String query = "SELECT * FROM cliente WHERE nome LIKE '%" + nome + "%'";
        return retrieve(query);
    }

    // TODO: implementar retriveByOwnerID


    @Override
    protected Animal buildObject(ResultSet rs) throws SQLException {
        return new Animal(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getInt("anoNasc"),
                rs.getInt("sexo")
        );
    }

    public void update(Animal animal) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("UPDATE animal SET nome=?, anoNasc=?, sexo=? WHERE id=?");
            stmt.setString(1, animal.getNome());
            stmt.setInt(2, animal.getIdade());
            stmt.setInt(3, animal.getSexo());
            this.executeUpdate(stmt);
        } catch (SQLException ex) {
            System.err.println("EXCEPTION: " + ex.getMessage());
        }

    }

    public void delete(Animal animal) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("DELETE FROM animal WHERE id = ?");
            stmt.setInt(1, animal.getId());
            this.executeUpdate(stmt);
        } catch (SQLException ex) {
            System.err.println("EXCEPTION: " + ex.getMessage());
        }
    }
}
