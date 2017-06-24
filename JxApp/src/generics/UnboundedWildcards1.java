/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Vladimir - Неограниченные метасимволы
 * Когда в записи используются только неограниченные метасимволы, как в примере Мар<?,?>, 
 * компилятор не отличает такой тип от Map. Кроме того, пример UnboundedWildcardsl.java показывает, 
 * что компилятор по-разному интерпретирует List<?> и List<? extends Object>. Ситуация осложняется тем, 
 * что компилятор не всегда интересуется различиями между List и List<?> (например), 
 * поэтому может показаться, что это одно и то же. В самом деле, поскольку параметризованный аргумент 
 * стирается до первого ограничения, List<?> кажется эквивалентным List<Object>, a List, по сути, 
 * тоже является List<Object> — однако ни одно из этих утверждений не является в полной мере истинным. 
 * List в действительности означает «низкоуровневый List, содержащий любой тип Object», 
 * тогда как List<?> означает «не-низкоуровневый List, содержащий какой-то конкретный тип, хотя мы не знаем, какой именно».
 * Во многих ситуациях, подобных рассмотренной, для компилятора совершенно не существенно, 
 * используется низкоуровневый тип или <?>. Конструкцию <?> можно считать обычным украшением; 
 * впрочем, она обладает некоторой практи­ческой ценностью, потому что фактически означает: 
 * «Код написан с учетом параметризации Java, и здесь эта конструкция означает не то, 
 * что я использую низкоуровневый тип, а то, что параметр параметризации может содержать про­извольный тип».
 */
public class UnboundedWildcards1 {
    static List list1;
    static List<?> list2;
    static List<? extends Object> list3;

    static void assign1(List list) {
        list1 = list;
        list2 = list;
        // list3 = list;// Предупреждение: непроверенное преобразование
        // Обнаружен List, требуется List<? extends Object>
    }

    static void assign2(List<?> list) {
        list1 = list;
        list2 = list;
        list3 = list;
    }

    static void assign3(List<? extends Object> list) {
        list1 = list;
        list2 = list;
        list3 = list;
    }

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
        assign1(new ArrayList());
        assign2(new ArrayList());
        // assign3(new ArrayList());// Предупреждение- 
        // Непроверенное преобразование. Обнаружен- ArrayList 
        // Требуется: List<? extends Object> 
        assign1(new ArrayList<String>());
        assign2(new ArrayList<String>());
        assign3(new ArrayList<String>());
        // Приемлемы обе формы- List<?>
        List<?> wildList = new ArrayList();
        wildList = new ArrayList<String>();
        assign1(wildList);
        assign2(wildList);
        assign3(wildList);

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
