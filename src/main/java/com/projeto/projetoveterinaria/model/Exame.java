package com.projeto.projetoveterinaria.model;

/**
 *
 * @author ariel
 */
public class Exame {

    private final int id;
    private int idConsulta;
    private String descricaoExame;

    public Exame(int id, int idConsulta, String descricaoExame) {
        this.id = id;
        this.idConsulta = idConsulta;
        this.descricaoExame = descricaoExame;
    }

    public int getId() {
        return id;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public String getDescricaoExame() {
        return descricaoExame;
    }

}
