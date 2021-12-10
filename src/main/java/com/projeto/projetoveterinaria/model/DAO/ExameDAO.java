package com.projeto.projetoveterinaria.model.DAO;

import com.projeto.projetoveterinaria.model.Exame;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ExameDAO extends DAO<Exame> {

    private static ExameDAO instance;
    public final static String TABLE_NAME = "exame";


    private ExameDAO() {
        super(TABLE_NAME);
        getConnection();
        createTable();
    }

    public static ExameDAO getInstance() {
        return (instance == null ? (instance = new ExameDAO()) : instance);
    }

    @Override
    protected Exame buildObject(ResultSet rs) throws SQLException {
        return new Exame(
                rs.getInt("id"),
                rs.getInt("id_consulta"),
                rs.getString("decricao_exame")
        );
    }

    protected String createQueryWithFK(String value, String column) {
        return getQuery(value, column);
    }

    public Exame create(int idConsulta, String descricaoExame) throws SQLException {
        PreparedStatement stmt = DAO.getConnection().prepareStatement("INSERT INTO exame (id_consulta,decricao_exame) VALUES (?,?)");
        stmt.setInt(1, idConsulta);
        stmt.setString(2, descricaoExame);
        executeUpdate(stmt);

        return retrieveLast();
    }


    public List<Exame> retrieveAll() {
        //language=SQL
        String query = "SELECT * FROM exame";
        return retrieve(query);
    }

    public Exame retrieveLast() {
        //language=SQL
        String query = "SELECT * FROM exame WHERE id = (SELECT max(id) FROM exame)";
        List<Exame> exame = retrieve(query);
        return exame.get(0);
    }

    public Exame retrieveById(int id) {
        //language=SQL
        String query = "SELECT * From exame WHERE id = " + id;
        List<Exame> exame = retrieve(query);
        return exame.get(0);
    }

    public List<Exame> retrieveByIdConsulta(int idConsulta) {
        //language=SQL
        String query = "SELECT * FROM exame WHERE id_consulta = " + idConsulta;
        return retrieve(query);
    }

    public void update(Exame exame) throws SQLException {
        PreparedStatement stmt = DAO.getConnection().prepareStatement("UPDATE exame SET id_consulta=?, decricao_exame=? WHERE id=?");
        stmt.setInt(1, exame.getIdConsulta());
        stmt.setString(2, exame.getDescricaoExame());
        stmt.setInt(3, exame.getId());
        executeUpdate(stmt);
    }

    public void delete(Exame exame) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("DELETE FROM exame WHERE  id = ?");
            stmt.setInt(1, exame.getId());
            executeUpdate(stmt);
        } catch (SQLException ex) {
            System.err.println("EXCEPTION: " + ex.getMessage());

        }
    }

    public int getNextId() {
        return nextId(TABLE_NAME);
    }
}
