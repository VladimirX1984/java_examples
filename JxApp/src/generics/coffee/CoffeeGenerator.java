/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generics.coffee;

import java.util.Iterator;
import java.util.Random;
import net.mindview.util.Generator;

/**
 *
 * @author Vladimir
 */
public class CoffeeGenerator implements Generator<Coffee>, Iterable<Coffee> {
    private final Class[] types = {Latte.class, Mocha.class, Cappuccino.class, Americano.class, Breve.class };

    private static Random rand = new Random(47);

    public CoffeeGenerator() {
    }
    //Для перебора
    private int size = 0;

    public CoffeeGenerator(int sz) {
        size = sz;
    }

    @Override
    public Coffee next() {
        try {
            return (Coffee)types[rand.nextInt(types.length)].newInstance();
            // Сообщение об ошибках во время выполнения:
        }
        catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    class CoffeeIterator implements Iterator<Coffee> {
        int count = size;

        @Override
        public boolean hasNext() {
            return count > 0;
        }

        @Override
        public Coffee next() {
            count--;
            return CoffeeGenerator.this.next();
        }

        @Override
        public void remove() {// He реализован
            throw new UnsupportedOperationException();
        }
    };

    @Override
    public Iterator<Coffee> iterator() {
        return new CoffeeIterator();
    }

    public static void main(String[] args) {
        CoffeeGenerator gen = new CoffeeGenerator();
        for (int i = 0; i < 5; i++) {
            System.out.println(gen.next());
        }
        for (Coffee c : new CoffeeGenerator(5)) {
            System.out.println(c);
        }
    }
}
