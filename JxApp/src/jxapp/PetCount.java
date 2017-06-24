/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jxapp;

import typeinfo.pets.Mouse;
import typeinfo.pets.ForNameCreator;
import typeinfo.pets.PetCreator;
import typeinfo.pets.Pet;
import typeinfo.pets.LiteralPetCreator;
import typeinfo.pets.Rodent;
import typeinfo.pets.Mutt;
import typeinfo.pets.Manx;
import typeinfo.pets.Dog;
import typeinfo.pets.Hamster;
import typeinfo.pets.Pug;
import typeinfo.pets.Rat;
import typeinfo.pets.Cat;
import java.util.HashMap;
import net.mindview.util.Print;

/**
 *
 * @author Vladimir
 */
public class PetCount {

    static class PetCounter extends HashMap<String, Integer> {

        public void count(String type) {
            Integer quantity = get(type);
            if (quantity == null) {
                put(type, 1);
            } else {
                put(type, quantity + 1);
            }
        }
    }

    public static void countPets(PetCreator creator) {
        PetCounter counter = new PetCounter();
        for (Pet pet : creator.createArray(20)) { // Подсчет всех объектов Pet: 
            Print.printnb(pet.getClass().getSimpleName() + " ");

            if (pet instanceof Pet) {
                counter.count("Pet");
            }
            if (pet instanceof Dog) {
                counter.count("Dog");
            }
            if (pet instanceof Mutt) {
                counter.count("Mutt");
            }
            if (pet instanceof Pug) {
                counter.count("Pug");
            }
            if (pet instanceof Cat) {
                counter.count("Cat");
            }
            if (pet instanceof Manx) {
                counter.count("EgyptіanMau");
            }
            if (pet instanceof Manx) {
                counter.count("Manx");
            }
            if (pet instanceof Manx) {
                counter.count("Cymric");
            }
            if (pet instanceof Rodent) {
                counter.count("Rodent");
            }
            if (pet instanceof Rat) {
                counter.count("Rat");
            }
            if (pet instanceof Mouse) {
                counter.count("Mouse");
            }
            if (pet instanceof Hamster) {
                counter.count("Hamster");
            }
        }
        // Вывод результатов подсчета.
        Print.print();
        Print.print(counter);
    }

    public static void main(String[] args) {
        Print.print("ForNameCreator");
        countPets(new ForNameCreator());
        Print.print();
        Print.print("LiteralPetCreator");
        countPets(new LiteralPetCreator());
    }
}
