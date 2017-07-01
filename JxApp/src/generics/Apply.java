/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generics;

/**
 *
 * @author Vladimir - компенсация отсутствия латентной типизации
 * Данный способ основан на применение метода к последовательности.
 * Но отражение работает относительно медленно из-за большого объема работы во время выполнения программы
 */
import java.lang.reflect.*;
import java.util.*;
import static net.mindview.util.Print.*;

public class Apply {
    public static <T, S extends Iterable<? extends T>>
        void apply(S seq, Method f, Object... args) {
        try {
            for (T t : seq) {
                f.invoke(t, args);
            }
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            // Failures are programmer errors
            throw new RuntimeException(e);
        }
    }
}

class Shape {
    public void rotate() {
        print(this + " rotate");
    }

    public void resize(int newSize) {
        print(this + " resize " + newSize);
    }
}

class Square extends Shape {
}

/**
 * С данным классом возникают некоторые затруднения. Чтобы тип  мог использоваться, 
 * он должен иметь конструктор по умолчанию (без аргументов)
 * @author Vladimir
 * @param <T> 
 */
class FilledList<T> extends ArrayList<T> {
    public FilledList(Class<? extends T> type, int size) {
        try {
            for (int i = 0; i < size; i++) {
                // Assumes default constructor: предполагается конструктор по умолчанию
                add(type.newInstance());
            }
        }
        catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
