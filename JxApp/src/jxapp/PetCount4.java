/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jxapp;

import net.mindview.util.TypeCounter;

import typeinfo.pets.Pet;
import typeinfo.pets.Pets;

import static net.mindview.util.Print.*;

/**
 *
 * @author Vladimir
 */
public class PetCount4 {

    public static void main(String[] args) {
        print("TypeCounter");
        TypeCounter counter = new TypeCounter(Pet.class);
        for (Pet pet : Pets.createArray(20)) {
            printnb(pet.getClass().getSimpleName() + " ");
            counter.count(pet);
        }
        print();
        print(counter);
    }
}
