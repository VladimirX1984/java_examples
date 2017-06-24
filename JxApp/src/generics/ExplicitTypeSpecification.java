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
 * @author Vladimir - Явное указание типа
 * Конечно, при этом теряются преимущества от использования класса New для уменьшения объема кода, 
 * но дополнительный синтаксис необходим только за пределами команд присваивания.
 */
public class ExplicitTypeSpecification {
    static void f(Map<Person, List<Pet>> petPeople) {
    }

    public static void main(String[] args) {
        f(New.<Person, List<Pet>>map());
    }
}
