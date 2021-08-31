package com.projeto.projetoveterinaria.model.DAO;

import com.projeto.projetoveterinaria.model.Exame;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ExameDAO extends DAO<Exame> {
    @Override
    protected Exame buildObject(ResultSet rs) throws SQLException {
        return new Exame(
                rs.getInt("id"),
                rs.getInt("id_consulta"),
                rs.getString("decricao_exame")
        );
    }

    public Exame create(int idConsulta, String descricaoExame) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("INSERT INTO exame (id_consulta,decricao_exame) VALUES (?,?)");
            stmt.setInt(1, idConsulta);
            stmt.setString(2, descricaoExame);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            System.err.println("EXCEPTION: " + ex.getMessage());
        }
        return retrieveLast();
    }

    public Exame retrieveLast() {
        //language=SQL
        String query = "SELECT * FROM exame WHERE id = (SELECT max(id) FROM exame)";
        List<Exame> exames = retrieve(query);
        return exames.get(0);
    }

    public List<Exame> retrieveAll() {
        //language=SQL
        String query = "SELECT * FROM exame";
        return retrieve(query);
    }

    public Exame retrieveById(int id) {
        //language=SQL
        String query = "SELECT * FROM cliente WHERE id = " + id;
        List<Exame> exame = retrieve(query);
        if (exame.isEmpty()) {
            throw new RuntimeException("Nenhum exame encontrado com id " + id);
        }
        return exame.get(0);
    }

    public List<Exame> retrieveByIdConsulta(int id) {
        //language=SQL
        String query = "SELECT * FROM exame WHERE id_consulta = " + id;
        return retrieve(query);
    }

    public void update(Exame exam) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("UPDATE exame SET id_consulta=?, decricao_exame=? WHERE id=?");
            stmt.setInt(1, exam.getIdConsulta());
            stmt.setString(2, exam.getDescricaoExame());
            stmt.setInt(3, exam.getId());
            executeUpdate(stmt);
        } catch (SQLException ex) {
            System.err.println("EXCEPTION: " + ex.getMessage());
        }
    }

    public void delete(Exame exam) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("DELETE FROM exame WHERE id=?");
            stmt.setInt(1, exam.getId());
            executeUpdate(stmt);
        } catch (SQLException ex) {
            System.err.println("EXCEPTION: " + ex.getMessage());
        }
    }
}
