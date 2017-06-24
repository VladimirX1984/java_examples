/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generics;

/**
 *
 * @author Vladimir - Метасимволы
 */
class Fruit {
}

class Apple extends Fruit {
}

class Jonathan extends Apple {
}

class Orange extends Fruit {
}

public class CovariantArrays {
    public static void main(String[] args) {
        Fruit[] fruit = new Apple[10];
        fruit[0] = new Apple(); // OK
        fruit[1] = new Jonathan(); // OK
        // Тип времени выполнения - Apple[], а не Fruit[] или Orange[]:
        try {
            // Компилятор позволяет добавлять объекты Fruit:
            fruit[0] = new Fruit(); // ArrayStoreException
        }
        catch (Exception e) {
            System.out.println(e);
        }
        try {
            // Компилятор позволяет добавлять объекты Orange:
            fruit[0] = new Orange(); // ArrayStoreException
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
