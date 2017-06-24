/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generics;

import java.util.Objects;

/**
 *
 * @author Vladimir - Метасимволы
 */
public class Holder<T> extends BasicHolder<T> {    

    public Holder() {
        super(null);
    }

    public Holder(T val) {
        super(val);
    }

    @Override
    public boolean equals(Object obj) {
        return get().equals(obj);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(get());
        return hash;
    }

    public static void main(String[] args) {
        Holder<Apple> Apple = new Holder<Apple>(new Apple());
        Apple d = Apple.get();
        Apple.set(d);
        // Holder<Fruit> Fruit = Apple; // Повышение невозможно
        Holder<? extends Fruit> fruit = Apple; // OK
        Fruit p = fruit.get();
        d = (Apple)fruit.get(); // Возвращает 'Object'
        try {
            Orange c = (Orange)fruit.get(); // Предупреждения нет
        }
        catch (Exception e) {
            System.out.println(e);
        }
        // fruit.set(new Apple()); // Вызов set() невозможен 
        // fruit.set(new Fruit()); // Вызов set() невозможен 
        System.out.println(fruit.equals(d)); // OK
    }
}
