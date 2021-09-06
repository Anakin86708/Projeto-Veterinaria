package com.projeto.projetoveterinaria.model.DAO;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Implementação DAO para acesso ao SQLite
 *
 * @author ariel
 */
public abstract class DAO<T> {

    public final static String DBURL = "jdbc:sqlite:vet2021.db";
    private static Connection connection;

    public static Connection getConnection() {
        if (DAO.connection == null) {
            // Estabilish new connection
            try {
                DAO.connection = DriverManager.getConnection(DAO.DBURL);
            } catch (SQLException e) {
                System.err.println("EXCEPTION: " + e.getMessage());
            }
        }
        return DAO.connection;
    }

    public static void closeConnection() {
        try {
            (DAO.getConnection()).close();
        } catch (SQLException e) {
            System.err.println("EXCEPTION: " + e.getMessage());
        }
    }

    protected int lastId(String tableName, String primaryKey) {
        Statement s;
        int lastId = -1;
        try {
            s = connection.createStatement();
            ResultSet rs = s.executeQuery("SELECT MAX(" + primaryKey + ") AS id FROM " + tableName);
            if (rs.next()) {
                lastId = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return lastId;
    }
    

    // Create table SQLite
    protected final boolean createTable() {
        try {
            // Table client:
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS cliente( \n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "nome VARCHAR, \n"
                    + "end VARCHAR, \n"
                    + "cep VARCHAR, \n"
                    + "email VARCHAR, \n"
                    + "telefone VARCHAR); \n");
            executeUpdate(stmt);
            // Table animal:
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS animal( \n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "nome VARCHAR, \n"
                    + "anoNasc INTEGER, \n"
                    + "sexo VARCHAR, \n"
                    + "id_especie INTEGER, \n"
                    + "id_cliente INTEGER); \n");
            executeUpdate(stmt);
            // Table species:
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS especie( \n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "nome VARCHAR); \n");
            executeUpdate(stmt);
            // Table vet:
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS vet( \n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "nome VARCHAR, \n"
                    + "endereco VARCHAR, \n"
                    + "telefone VARCHAR); \n");
            executeUpdate(stmt);
            // Table treatment:
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS tratamento( \n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "id_animal INTEGER, \n"
                    + "nome VARCHAR, \n"
                    + "dataIni TEXT, \n"
                    + "dataFim TEXT, \n"
                    + "terminado INTEGER); \n");
            executeUpdate(stmt);
            // Table appointment:
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS consulta( \n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "data DATE, \n"
                    + "comentario VARCHAR, \n"
                    + "id_animal INTEGER, \n"
                    + "id_vet INTEGER, \n"
                    + "id_tratamento INTEGER, \n"
                    + "terminado BOOLEAN); \n");
            executeUpdate(stmt);
            // Table exam:
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS exame( \n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "decricao_exame VARCHAR, \n"
                    + "id_consulta INTEGER); \n");
            executeUpdate(stmt);
            return true;
        } catch (SQLException ex) {
            System.err.println("EXCEPTION: " + ex.getMessage());
        }
        return false;
    }

    protected abstract T buildObject(ResultSet rs) throws SQLException;

    public List<T> retrieve(String query) {
        ResultSet rs = this.getResultSet(query);
        List<T> clients = new ArrayList<>();
        try {
            while (rs.next()) {
                clients.add(buildObject(rs));
            }
        } catch (SQLException ex) {
            System.err.println("EXCEPTION: " + ex.getMessage());
        }
        return clients;
    }

    protected int executeUpdate(PreparedStatement queryStatement) throws SQLException {
        return queryStatement.executeUpdate();
    }

    protected ResultSet getResultSet(String query) {
        try {
            return (connection.createStatement()).executeQuery(query);
        } catch (SQLException ex) {
            System.err.println("EXCEPTION: " + ex.getMessage());
        }
        return null;
    }
}
