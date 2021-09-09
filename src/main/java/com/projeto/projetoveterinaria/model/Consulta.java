package com.projeto.projetoveterinaria.model;

import java.util.Calendar;

/**
 *
 * @author ariel
 */
public class Consulta {

    private final int id;
    private Calendar data;
    private int hora;
    private String comentarios;
    private int idAnimal;
    private int idTratamento;
    private int idVeterinario;
    private boolean terminou;

    public Consulta(int id, Calendar data, int hora, String comentarios, int idAnimal, int idTratamento, int idVeterinario, boolean terminou) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.comentarios = comentarios;
        this.idAnimal = idAnimal;
        this.idTratamento = idTratamento;
        this.idVeterinario = idVeterinario;
        this.terminou = terminou;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public int getIdTratamento() {
        return idTratamento;
    }

    public void setIdTratamento(int idTratamento) {
        this.idTratamento = idTratamento;
    }

    public int getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdVeterinario(int idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public boolean getTerminou() {
        return terminou;
    }

    public void setTerminou(boolean terminou) {
        this.terminou = terminou;
    }

    public int getId() {
        return id;
    }
}
