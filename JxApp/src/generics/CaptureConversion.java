/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generics;

/**
 *
 * @author Vladimir - Фиксация
 */
public class CaptureConversion {
    static <T> void f1(Holder<T> holder) {
        T t = holder.get();
        System.out.println(t.getClass().getSimpleName());
    }
    
    static <T> void f2(Holder<?> holder) {
        f1(holder); // Вызов с зафиксированным типом
    }
    
    public static void main(String[] args) {
        Holder raw = new Holder<Integer>(1);
        // f1(raw) // Выдает предупреждение
        f2(raw); // Без предупреждений
        Holder rawBasic = new Holder();
        rawBasic.set(new Object()); // предупреждение
        f2(rawBasic); // Без предупреждений
        // Восходящее преобразование к Holder<?>,
        // тип все равно определяется правильно
        Holder<?> wildcarded = new Holder<Double>(1.0);
        f2(wildcarded);
        f1(wildcarded);
    }
}
