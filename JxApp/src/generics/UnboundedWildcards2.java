/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generics;

/**
 *
 * @author Vladimir
 * Второй пример демонстрирует важное практическое использование неограниченных метасимволов.
 * Когда вы имеете дело с несколькими параметрами, иногда важно указать, что один параметр
 * может относиться к произвольному типу, а другой ограничить определенным типом:
 */
import java.util.*;

public class UnboundedWildcards2 {
    static Map map1;
    static Map<?, ?> map2;
    static Map<String, ?> map3;

    static void assign1(Map map) {
        map1 = map;
    }

    static void assign2(Map<?, ?> map) {
        map2 = map;
    }

    static void assign3(Map<String, ?> map) {
        map3 = map;
    }

    public static void main(String[] args) {
        assign1(new HashMap());
        assign2(new HashMap());
        // assign3(new HashMap()); // Предупреждение: 
        // Непроверенное преобразование. Обнаружен: HashMap 
        // Требуется: Map<String,?>
        assign1(new HashMap<String, Integer>());
        assign2(new HashMap<String, Integer>());
        assign3(new HashMap<String, Integer>());
    }
}
