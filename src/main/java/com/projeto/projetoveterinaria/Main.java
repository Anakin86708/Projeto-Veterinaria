package com.projeto.projetoveterinaria;

import com.projeto.projetoveterinaria.model.*;
import com.projeto.projetoveterinaria.model.DAO.*;

import java.sql.SQLException;
import java.util.Calendar;

/**
 * @author Ariel Tadeu da Silva
 */
public class Main {

    public static void main(String[] args) {

        // Cadastrando clientes
        Cliente eu = null;
        Cliente charles = null;
        try {
            eu = ClienteDAO.getInstance().create(
                    "Ariel",
                    "Rua 123",
                    "13280000",
                    "a231602@dac.unicamp.br",
                    "9888023"
            );

            charles = ClienteDAO.getInstance().create(
                    "Charles",
                    "Rua 1",
                    "13089000",
                    "charles@icloud.com",
                    "1233210"
            );
        } catch (SQLException e) {
            System.err.println("EXCEPTION: " + e.getMessage());
            System.exit(1);
        }


        // Criando espécies
        Especie gato = EspecieDAO.getInstance().create("Gato");
        Especie cachorro = EspecieDAO.getInstance().create("Cachorro");

        // Cadastrando veterinário
        Veterinario vet = VeterinarioDAO.getInstance().create("Dr. Carlos", "Av. Brasil, 1", "(19) 9999-9999");

        // Cadastrando animais
        AnimalDAO.getInstance().create("Holly", 2013, Sexo.FEMEA, gato, eu);
        AnimalDAO.getInstance().create("Nina", 2019, Sexo.FEMEA, gato, eu);
        AnimalDAO.getInstance().create("Belinha", 2019, Sexo.FEMEA, gato, eu);
        AnimalDAO.getInstance().create("Dorinha", 2019, Sexo.FEMEA, gato, eu);

        Animal max = AnimalDAO.getInstance().create("Max", 2020, Sexo.MACHO, cachorro, charles);
        AnimalDAO.getInstance().create("Bob", 2015, Sexo.FEMEA, cachorro, charles);

        // Criando tratamento
        Tratamento trat = TratamentoDAO.getInstance().create("Procedimento padrão 01c", Calendar.getInstance(), Calendar.getInstance(), max.getId(), true);

        // Criando uma consulta
        Consulta consulta = ConsultaDAO.getInstance().create(Calendar.getInstance(), 10, "Investigar pata traseira", max.getId(), trat.getId(), vet.getId(), true);

        // Criando um exame
        ExameDAO.getInstance().create(consulta.getId(), "Raio-x");

        System.out.println("Clientes: listar todos");
        System.out.println(ClienteDAO.getInstance().retrieveAll());

        System.out.println("Animais: listar todos");
        System.out.println(AnimalDAO.getInstance().retrieveAll());
        System.out.println("Animais: listar por id do cliente");
        System.out.println(AnimalDAO.getInstance().retriveByOwnerID(eu.getId()));

        System.out.println("Veterinários: listar por nome similar");
        System.out.println(VeterinarioDAO.getInstance().retrieveBySimilarName("los"));

        System.out.println("Tratamento: listar todos");
        System.out.println(TratamentoDAO.getInstance().retrieveAll());

        System.out.println("Exame: listar por id consulta");
        System.out.println(ExameDAO.getInstance().retrieveByIdConsulta(consulta.getId()));

        System.out.println("Consulta: listar última");
        System.out.println(ConsultaDAO.getInstance().retrieveLast());


    }
}
