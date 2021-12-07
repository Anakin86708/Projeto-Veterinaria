package com.projeto.projetoveterinaria.model.DAO;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação DAO para acesso ao SQLite
 *
 * @author ariel
 */
public abstract class DAO<T> {

    public final static String DBURL = "jdbc:sqlite:vet2021.db";
    public final String columnName;
    private static Connection connection;

    protected DAO(String columnName) {
        this.columnName = columnName;
    }

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

    public String[] getColumnsNames() {
        PreparedStatement stmt;
        ArrayList<String> columns = new ArrayList<>();
        try {
            stmt = connection.prepareStatement("SELECT * FROM ? LIMIT 1");
            stmt.setString(1, columnName);
            ResultSet resultSet = stmt.executeQuery();
            ResultSetMetaData metadata = resultSet.getMetaData();

            int columnCount = metadata.getColumnCount();
            for (int i = 1; i < columnCount; i++) {
                String columnName = metadata.getColumnName(i);
                columns.add(columnName);
            }
        } catch (SQLException e) {
            System.err.println("EXCEPTION: " + e.getMessage());
        }
        return  columns.toArray(new String[0]);
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

    protected int nextId(String tableName) {
        return lastId(tableName, "ROWID") + 1;
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
                    + "dataIni INTEGER, \n"
                    + "dataFim INTEGER, \n"
                    + "terminado INTEGER); \n");
            executeUpdate(stmt);
            // Table appointment:
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS consulta( \n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "data INTEGER, \n"
                    + "horario VARCHAR, \n"
                    + "comentario VARCHAR, \n"
                    + "id_animal INTEGER, \n"
                    + "id_vet INTEGER, \n"
                    + "id_tratamento INTEGER, \n"
                    + "terminado INTEGER); \n");
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

    public List<T> retrieveBySimilarValueOnColumn(String value, String column) {
        //language=SQL
        String query = "SELECT * FROM " + columnName +" WHERE " + column + " LIKE '%" + value + "%'";
        return retrieve(query);
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
