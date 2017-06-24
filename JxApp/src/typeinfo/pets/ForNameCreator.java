/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typeinfo.pets;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vladimir
 */
public class ForNameCreator extends PetCreator {

    private static final List<Class<? extends Pet>> types = new ArrayList<Class<? extends Pet>>();
    private static final String[] typeNames = {
        "typeinfo.pets.Mutt",
        "typeinfo.pets.Pug",
        "typeinfo.pets.EgyptianMau",
        "typeinfo.pets.Manx",
        "typeinfo.pets.Cymric",
        "typeinfo.pets.Rat",
        "typeinfo.pets.Mouse",
        "typeinfo.pets.Hamster"
    };

    @SuppressWarnings("unchecked")
// загрузчик списка классов
    private static void loader() {
        try { //при загрузке класс приводится к типу заданному типизациии контейнера
            for (String name : typeNames) {
                types.add((Class<? extends Pet>) Class.forName(name));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
// статический блок ОДНОКРТАНО загружающий список при инициализации данного класса

    static {
        loader();
    }

    @Override
    public List<Class<? extends Pet>> getTypes() {
        return types;
    }
}
