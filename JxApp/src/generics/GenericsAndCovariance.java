/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generics;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vladimir - Метасимволы
 * Но иногда между двумя разновидностями параметризованных типов все же требуется установить некоторую связь, аналогичную восходящему преобразованию.
 * Именно это и позволяют сделать метасимволы.
 */
public class GenericsAndCovariance {
    public static void main(String[] args) {
        // Метасимволы обеспечивают ковариантность:
        List<? extends Fruit> flist = new ArrayList<Apple>();
        // Ошибка компиляции: добавление объекта
        // произвольного типа невозможно
        // flist.add(new Apple());
        // flist.add(new Fruit());
        // flist.add(new Object());
        flist.add(null); // Можно, но неинтересно
        // Мы знаем, что возвращается по крайней мере Fruit:
        Fruit f = flist.get(0);
    }
}
