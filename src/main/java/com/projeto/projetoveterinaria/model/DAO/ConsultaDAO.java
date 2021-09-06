package com.projeto.projetoveterinaria.model.DAO;

import com.projeto.projetoveterinaria.model.Consulta;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ConsultaDAO extends DAO<Consulta> {
    @Override
    protected Consulta buildObject(ResultSet rs) throws SQLException {
        return new Consulta(
                rs.getInt("id"),
                rs.getDate("data").toInstant(),
                rs.getString("comentario"),
                rs.getInt("id_animal"),
                rs.getInt("id_tratamento"),
                rs.getInt("id_vet"),
                rs.getBoolean("terminado")
        );
    }

    public Consulta create(Date data, String comentario, int id_animal, int id_tratamento, int id_vet, boolean terminado) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("INSERT INTO consulta (data,comentario,id_animal,id_vet,id_tratamento,terminado) VALUES (?,?,?,?,?,?)");
            stmt.setDate(1, data);
            stmt.setString(2, comentario);
            stmt.setInt(3, id_animal);
            stmt.setInt(4, id_vet);
            stmt.setInt(5, id_tratamento);
            stmt.setBoolean(6, terminado);
            this.executeUpdate(stmt);
        } catch (SQLException ex) {
            System.err.println("EXCEPTION: " + ex.getMessage());
        }
        return retrieveLast();
    }

    public Consulta retrieveLast() {
        //language=SQL
        String query = "SELECT * FROM consulta WHERE id = (SELECT max(id) FROM consulta)";
        List<Consulta> consulta = retrieve(query);
        return consulta.get(0);
    }

    public List<Consulta> retrieveAll() {
        //language=SQL
        String query = "SELECT * from consulta";
        return retrieve(query);
    }

    public Consulta retrieveById(int id) {
        //language=SQL
        String query = "SELECT * from consulta WHERE id = " + id;
        List<Consulta> consulta = retrieve(query);
        return consulta.get(0);
    }

    public List<Consulta> retrieveByIdAnimal(int id_animal) {
        //language=SQL
        String query = "SELECT * FROM consulta WHERE id_animal = " + id_animal;
        return retrieve(query);
    }

    public List<Consulta> retrieveByIdTratamento(int id_tratamento) {
        //language=SQL
        String query = "SELECT * FROM consulta WHERE id_tratamento = " + id_tratamento;
        return retrieve(query);
    }

    public List<Consulta> retrieveByIdVeterinario(int id_vet) {
        //language=SQL
        String query = "SELECT * FROM consulta WHERE id_vet = " + id_vet;
        return retrieve(query);
    }

    public void update(Consulta consulta) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("UPDATE consulta SET data=?, comentario=?, id_animal=?, id_tratamento=?, id_vet=?, terminado=? WHERE id = ?");
            stmt.setDate(1, (Date) Date.from(consulta.getData()));
            stmt.setString(2, consulta.getComentarios());
            stmt.setInt(3, consulta.getIdAnimal());
            stmt.setInt(4, consulta.getIdTratamento());
            stmt.setInt(5, consulta.getIdVeterinario());
            stmt.setBoolean(6, consulta.getTerminou());
            stmt.setInt(7, consulta.getId());
            this.executeUpdate(stmt);
        } catch (SQLException ex) {
            System.err.println("EXCEPTION: " + ex.getMessage());
        }
    }

    public void delete(Consulta consulta) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("DELETE FROM consulta WHERE  id = ?");
            stmt.setInt(1, consulta.getId());
            this.executeUpdate(stmt);
        } catch (SQLException ex) {
            System.err.println("EXCEPTION: " + ex.getMessage());

        }
    }
}
