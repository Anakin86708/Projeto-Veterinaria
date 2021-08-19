package com.projeto.projetoveterinaria.model;

/**
 *
 * @author ariel
 */
public class Exame {

    private int idConsulta;
    private String descricaoExame;

    public Exame(int idConsulta, String descricaoExame) {
        this.idConsulta = idConsulta;
        this.descricaoExame = descricaoExame;
    }

    public String getDescricaoExame() {
        return descricaoExame;
    }

}
