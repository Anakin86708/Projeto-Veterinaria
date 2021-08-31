package com.projeto.projetoveterinaria.model.DAO;

import com.projeto.projetoveterinaria.model.Tratamento;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TratamentoDAO extends DAO<Tratamento> {
    @Override
    protected Tratamento buildObject(ResultSet rs) throws SQLException {
        return new Tratamento(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getDate("dataEntrada"),
                rs.getDate("dataSaida"),
                rs.getInt("idAnimal"),
                rs.getBoolean("terminou")
        );
    }
}
