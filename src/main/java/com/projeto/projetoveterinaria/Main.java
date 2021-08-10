package com.projeto.projetoveterinaria;

import com.projeto.projetoveterinaria.Cliente;

/**
 *
 * @author Ariel Tadeu da Silva
 */
public class Main {

    public static void main(String[] args) {
        Cliente eu = new Cliente(0, "Ariel", "Rua 123", "9888023", "13280000", "a231602@dac.unicamp.br");

        Animal[] meusGatos = {
            new Animal(0, "Holly", 8, 1), 
            new Animal(1, "Nina", 4, 1), 
            new Animal(2, "Belinha", 3, 1),
            new Animal(3, "Dorinha", 3, 1)
        };

        for (Animal gato: meusGatos) {
            eu.adicinarAnimal(gato);
        }
        
        System.out.println(eu);
    }
}
