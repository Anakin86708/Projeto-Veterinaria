package com.projeto.projetoveterinaria.model.DAO;

import com.projeto.projetoveterinaria.model.Exame;

impiort java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    private Exame retrieveLast() {
        String q
    }
}
