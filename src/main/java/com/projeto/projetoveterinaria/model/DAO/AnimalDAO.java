package com.projeto.projetoveterinaria.model.DAO;

import com.projeto.projetoveterinaria.model.Animal;
import com.projeto.projetoveterinaria.model.Cliente;
import com.projeto.projetoveterinaria.model.Especie;
import com.projeto.projetoveterinaria.model.Sexo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AnimalDAO extends DAO<Animal> {

    private static AnimalDAO instance;
    public final static String TABLE_NAME = "animal";


    private AnimalDAO() {
        super(TABLE_NAME);
        getConnection();
        createTable();
    }

    public static AnimalDAO getInstance() {
        return (instance == null ? (instance = new AnimalDAO()) : instance);
    }

    public Animal create(String nome, int anoNasc, Sexo sexo, Especie especie, Cliente cliente) throws SQLException {
        int idEspecie = especie.getId();
        int idCliente = cliente.getId();

        return insertAnimal(nome, anoNasc, sexo, idEspecie, idCliente);
    }

    public Animal create(String nome, int anoNasc, Sexo sexo, int idEspecie, int idCliente) throws SQLException {
        return insertAnimal(nome, anoNasc, sexo, idEspecie, idCliente);
    }

    private Animal insertAnimal(String nome, int anoNasc, Sexo sexo, int idEspecie, int idCliente) throws SQLException {
        PreparedStatement stmt = DAO.getConnection().prepareStatement("INSERT INTO animal (nome, anoNasc, sexo, id_especie, id_cliente) VALUES (?,?,?,?,?)");
        stmt.setString(1, nome);
        stmt.setInt(2, anoNasc);
        stmt.setString(3, sexo.toString());
        stmt.setInt(4, idEspecie);
        stmt.setInt(5, idCliente);
        this.executeUpdate(stmt);
        return retrieveById(lastId("animal", "id"));
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
                Sexo.valueOf(rs.getString("sexo")),
                rs.getInt("id_especie"),
                rs.getInt("id_cliente")
        );
    }

    public void update(Animal animal) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("UPDATE animal SET nome=?, anoNasc=?, sexo=?, id_especie=?, id_cliente=? WHERE id=?");
            stmt.setString(1, animal.getNome());
            stmt.setInt(2, animal.getAnoNasc());
            stmt.setString(3, animal.getSexo().toString());
            stmt.setInt(4, animal.getIdEspecie());
            stmt.setInt(5, animal.getIdCliente());
            stmt.setInt(6, animal.getId());
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

    public int getNextId() {
        return nextId(TABLE_NAME);
    }
}
