package com.projeto.projetoveterinaria.model.DAO;

import com.projeto.projetoveterinaria.model.Animal;

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
        //language=SQL
        String query = "SELECT * FROM animal";
        return retrieve(query);
    }

    public Animal retrieveById(int id) {
        //language=SQL
        String query = "SELECT * FROM animal WHERE id = " + id;
        List<Animal> client = retrieve(query);
        if (client.isEmpty()) {
            throw new RuntimeException("Nenhum animal encontrado com id " + id);
        }
        return client.get(0);
    }

    public List<Animal> retrieveBySimilarName(String nome) {
        //language=SQL
        String query = "SELECT * FROM animal WHERE nome LIKE '%" + nome + "%'";
        return retrieve(query);
    }

    public List<Animal> retriveByOwnerID(int id_cliente) {
        //language=SQL
        String query = "SELECT * FROM animal WHERE id_cliente = " + id_cliente;
        return retrieve(query);
    }


    @Override
    protected Animal buildObject(ResultSet rs) throws SQLException {
        return new Animal(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getInt("anoNasc"),
                rs.getInt("sexo"),
                rs.getInt("id_especie"),
                rs.getInt("id_cliente")
        );
    }

    public void update(Animal animal) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("UPDATE animal SET nome=?, anoNasc=?, sexo=?, id_especie=?, id_cliente=? WHERE id=?");
            stmt.setString(1, animal.getNome());
            stmt.setInt(2, animal.getAnoNasc());
            stmt.setInt(3, animal.getSexo());
            stmt.setInt(4, animal.getIdEspecie());
            stmt.setInt(5,animal.getIdCliente());
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
