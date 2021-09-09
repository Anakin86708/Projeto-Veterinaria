package com.projeto.projetoveterinaria;

import com.projeto.projetoveterinaria.model.Animal;
import com.projeto.projetoveterinaria.model.Cliente;
import com.projeto.projetoveterinaria.model.DAO.AnimalDAO;
import com.projeto.projetoveterinaria.model.DAO.ClienteDAO;

/**
 * @author Ariel Tadeu da Silva
 */
public class Main {

    public static void main(String[] args) {
        String nome = "Ariel";
        String endereco = "Rua 123";
        String cep = "13280000";
        String email = "a231602@dac.unicamp.br";
        String telefone = "9888023";

        Cliente eu = ClienteDAO.getInstance().create(nome, endereco, cep, email, telefone);
        Animal[] meusGatos = {
                new Animal(0, "Holly", 2013, 1, 0, 0),
                new Animal(1, "Nina", 2019, 1, 0, 0),
                new Animal(2, "Belinha", 2019, 1, 0, 0),
                new Animal(3, "Dorinha", 2019, 1, 0, 0)
        };
        for (Animal gato : meusGatos) {
            AnimalDAO.getInstance().create(gato.getNome(), gato.getAnoNasc(), gato.getSexo(), gato.getIdEspecie(), eu);
        }

        Cliente cliente2 = ClienteDAO.getInstance().create(
                "Charles",
                "Rua 1",
                "13089000",
                "charles@icloud.com",
                "123321"
        );
        Animal[] cachorros = {
                new Animal(4, "Max", 2020, 0, 1, 1),
                new Animal(4, "Bob", 2015, 1, 1, 1),
        };
        for (Animal c: cachorros){
            AnimalDAO.getInstance().create(c.getNome(),c.getAnoNasc(),c.getSexo(),c.getIdEspecie(),cliente2);
        }
        System.out.println("Clientes: listar todos");
        System.out.println(ClienteDAO.getInstance().retrieveAll());

        System.out.println("Animais: listar todos");
        System.out.println(AnimalDAO.getInstance().retrieveAll());
        System.out.println("Animais: listar por id do cliente");
        System.out.println(AnimalDAO.getInstance().retriveByOwnerID(eu.getId()));

    }
}
