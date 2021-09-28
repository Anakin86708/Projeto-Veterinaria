package com.projeto.projetoveterinaria.model.DAO;

import com.projeto.projetoveterinaria.model.Consulta;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

public class ConsultaDAO extends DAO<Consulta> {

    private static ConsultaDAO instance;

    private ConsultaDAO() {
        getConnection();
        createTable();
    }

    public static ConsultaDAO getInstance() {
        return (instance == null ? (instance = new ConsultaDAO()) : instance);
    }

    @Override
    protected Consulta buildObject(ResultSet rs) throws SQLException {
        Calendar data = Calendar.getInstance();
        data.setTimeInMillis(rs.getLong("data"));
        return new Consulta(
                rs.getInt("id"),
                data,
                rs.getInt("horario"),
                rs.getString("comentario"),
                rs.getInt("id_animal"),
                rs.getInt("id_tratamento"),
                rs.getInt("id_vet"),
                rs.getBoolean("terminado")
        );
    }

    public Consulta create(Calendar data, int horario, String comentario, int idAnimal, int idTratamento, int idVeterinario, boolean terminou) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("INSERT INTO consulta (data, horario, comentario, id_animal, id_vet, id_tratamento, terminado) VALUES (?,?,?,?,?,?,?)");
            stmt.setDate(1, new Date(data.getTimeInMillis()));
            stmt.setInt(2, horario);
            stmt.setString(3, comentario);
            stmt.setInt(4, idAnimal);
            stmt.setInt(5, idVeterinario);
            stmt.setInt(6, idTratamento);
            stmt.setBoolean(7, terminou);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            System.err.println("EXCEPTION: " + ex.getMessage());
        }
        return retrieveLast();
    }

    public Consulta retrieveLast() {
        //language=SQL
        String query = "SELECT * FROM consulta WHERE id = (SELECT max(id) FROM consulta)";
        List<Consulta> consultas = retrieve(query);
        return consultas.get(0);
    }

    public List<Consulta> retrieveAll() {
        //language=SQL
        String quary = "SELECT * FROM consulta";
        return retrieve(quary);
    }

    public Consulta retrieveById(int id) {
        //language=SQL
        String quary = "SELECT * FROM consulta WHERE id = " + id;
        List<Consulta> consultas = retrieve(quary);
        return consultas.get(0);
    }

    public List<Consulta> retrieveByIdAnimal(int idAnimal) {
        //language=SQL
        String quary = "SELECT * FROM consulta WHERE id_animal = " + idAnimal;
        return retrieve(quary);
    }

    public List<Consulta> retrieveByIdVeterinario(int idAnimal) {
        //language=SQL
        String quary = "SELECT * FROM consulta WHERE id_vet = " + idAnimal;
        return retrieve(quary);
    }

    public List<Consulta> retrieveByIdTratamento(int idAnimal) {
        //language=SQL
        String quary = "SELECT * FROM consulta WHERE id_tratamento = " + idAnimal;
        return retrieve(quary);
    }

    public void update(Consulta consulta) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("UPDATE consulta SET data=?, horario=?, comentario=?, id_animal=?, id_tratamento=?, id_vet=?, terminado=? WHERE id=?");
            stmt.setDate(1, new Date(consulta.getData().getTimeInMillis()));
            stmt.setInt(2, consulta.getHora());
            stmt.setString(3, consulta.getComentarios());
            stmt.setInt(4, consulta.getIdAnimal());
            stmt.setInt(5, consulta.getIdTratamento());
            stmt.setInt(6, consulta.getIdVeterinario());
            stmt.setBoolean(7, consulta.getTerminou());
            stmt.setInt(8, consulta.getId());
            executeUpdate(stmt);
        } catch (SQLException ex) {
            System.err.println("EXCEPTION: " + ex.getMessage());
        }
    }
}
