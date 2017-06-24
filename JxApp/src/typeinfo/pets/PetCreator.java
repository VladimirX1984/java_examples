/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typeinfo.pets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Vladimir
 */
public abstract class PetCreator {

    //инициализация генератора случайных чисел
    private final Random rand = new Random(47);

    //абстрактный метод возвращает список возможных типов(классов) животных 
    public abstract List<Class<? extends Pet>> getTypes();

    public Pet randomPet() { // Создание одного случайного объекта Pet 
        // генерация случайного номера в диапазоне списка классов животных
        int n = rand.nextInt(getTypes().size());
        try {//создание нового объекта для класса с данным номером в списке классов 
            return getTypes().get(n).newInstance();
        }
        catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Pet[] createArray(int size) {//создание массива случайных Pet 
        Pet[] result = new Pet[size];
        for (int i = 0; i < size; i++) {
            result[i] = randomPet();
        }
        return result;
    }

    public ArrayList<Pet> arrayList(int size) {// создание списка случайных Pet
        ArrayList<Pet> result = new ArrayList<>();
        Collections.addAll(result, createArray(size));
        return result;
    }
}
