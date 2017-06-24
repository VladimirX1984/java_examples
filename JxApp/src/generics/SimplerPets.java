/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generics;

import java.util.List;
import java.util.Map;
import net.mindview.util.New;
import typeinfo.pets.Pet;
import typeinfo.pets.Person;

/**
 *
 * @author Vladimir - Вычисление типа аргумента
 */
public class SimplerPets {
    public static void main(String[] args) {
        Map<Person, List<? extends Pet>> petPeople = New.map();
        // Rest of the code is the same...
    }
}
