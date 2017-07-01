/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generics;

import generics.coffee.Coffee;
import generics.coffee.Latte;
import generics.coffee.Mocha;
import java.util.*;
import net.mindview.util.Generator;
import static net.mindview.util.Print.print;

/**
 *
 * @author Vladimir - Моделирование латентной типизации с использованием адаптеров
 * Обобщения Java не имеют латентной типизации, а для написания кода, применимого между границами классов
 * (то есть действительно «обобщенного» кода), необходимо некое подобие латентной типизации.
 * Можно ли как-то обойти это ограничение?
 * Что здесь дает латентная типизация? По сути вы пишете код, который означает:
 * «Неважно, какой тип я здесь использую, при условии, что он содержит эти методы».
 * Латентная типизация в некотором смысле создает неявный интерфейс, содержащий нужные методы.
 * Получается, что если мы напишем необходимый интерфейс вручную
 * (поскольку Java не сделает это за нас), это должно решить проблему.
 */
interface Addable<T> {
    void add(T t);
}

class Fill {
    // Версия с маркером:
    public static <T> void fill(Addable<T> addable, Class<? extends T> classToken, int size) {
        for (int i = 0; i < size; ++i) {
            try {
                addable.add(classToken.newInstance());
            }
            catch (IllegalAccessException | InstantiationException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // версия с генератором:
    public static <T> void fill(Addable<T> addable, Generator<T> generator, int size) {
        for (int i = 0; i < size; ++i) {
            addable.add(generator.next());
        }
    }
}
// Для адаптации базового типа необходимо использовать композицию. 
// Включение поддержки Addable в произвольный контейнер Collection 
// с использованием композиции:
class AddableCollectionAdapter<T> implements Addable<T> {
    private final Collection<T> c;

    public AddableCollectionAdapter(Collection<T> c) {
        this.c = c;
    }

    @Override
    public void add(T item) {
        c.add(item);
    }
}

// Вспомогательный класс для автоматической фиксации типа: 
class Adapter {
    public static <T> Addable<T> collectionAdapter(Collection<T> c) {
        return new AddableCollectionAdapter<T>(c);
    }
}

// Для адаптации конкретного типа можно применить наследование.
// Включение Addable в SimpleQueue Addable посредством наследования:
class AddableSimpleQueue<T> extends SimpleQueue<T> implements Addable<T> {
    @Override
    public void add(T item) {
        super.add(item);
    }
}

/** 
 * Класс Fill не требует Collection. Вместо этого ему требуется любой объект, реализующий Addable, 
 * а интерфейс Addable был написан только для Fill — это проявление латентного типа, 
 * который компилятор должен был создать для меня.
 * В этой версии также был добавлен перегруженный метод fill(), получающий Generator вместо маркера типа. 
 * Тип Generator безопасен на стадии компиляции: компилятор следит за тем, чтобы передавался правильный объект Generator, 
 * так что исключения возбуждаться не будут.
 * Первый адаптер AddableCollectionAdapter работает с базовым типом Collection; 
 * это означает, что может использоваться любая реализация Collection. 
 * Эта версия просто сохраняет ссылку на Collection н использует сс для реализации add().
 * Если вместо базового класса иерархии используется конкретный тип, 
 * это позволяет несколько сократить объем кода при создании адаптера с применением наследования,
 * как видно на примере AddableSimpleQueue.
*/
public class FillTest {
    public static void main(String[] args) {
        // Адаптация Collection:
        List<Coffee> carrier = new ArrayList<Coffee>();
        Fill.fill(new AddableCollectionAdapter<Coffee>(carrier), Coffee.class, 3);
        // Вспомогательный метод фиксирует тип: 
        Fill.fill(Adapter.collectionAdapter(carrier), Coffee.class, 2);
        carrier.forEach((c) -> {
            print(c);
        });
        print("----------------------");
        // Использование адаптированного класса: 
        AddableSimpleQueue<Coffee> coffeeQueue = new AddableSimpleQueue<Coffee>();
        Fill.fill(coffeeQueue, Mocha.class, 4);
        Fill.fill(coffeeQueue, Latte.class, 1);
        for (Coffee c : coffeeQueue) {
            print(c);
        }
    }
}
