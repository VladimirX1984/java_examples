/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generics;

import java.math.*;
import java.util.concurrent.atomic.*;
import java.util.*;
import static net.mindview.util.Print.print;

/**
 *
 * @author Vladimir - Использование объектов функций как стратегий.
 * В последнем примере мы создадим полноценный обобщенный коде использованием методологии адаптеров из предыдущего раздела.
 * Пример начинался с попытки вычисления суммы по последовательности элементов (любого типа, поддерживающего суммирование),
 * но поднялся до выполнения общих операций с использованием функционального стиля программирования.
 * Задача решается использованием паттерна проектирования «Стратегия»,
 * который порождает более элегантный код, полностью изолирующий «то, что изменяется» внутри объекта функции (функтора).
 * Объект функции — объект, который в какой-то мере ведет себя как функция, — как правило,
 * он содержит всего один метод, представляющий интерес (в языках, поддерживающих перегрузку операторов,
 * вызов этого метода может выглядеть как обычное применение оператора).
 */
// Разные типы объектов функций:
interface Combiner<T> {
    T combine(T x, T у);
}

interface UnaryFunction<R, T> {
    R function(T x);
}

interface Collector<T> extends UnaryFunction<T, T> {
    T result(); // Извлечение результата из параметра-накопителя
}

interface UnaryPredicate<T> {
    boolean test(T x);
}

public class Functional {
    // Вызывает объект Combiner для каждого элемента, 
    // чтобы объединить его с накапливаемым результатом, 
    // который в конечном итоге возвращается: 
    public static <T> T reduce(Iterable<T> seq, Combiner<T> combiner) {
        Iterator<T> it = seq.iterator();
        if (it.hasNext()) {
            T result = it.next();
            while (it.hasNext()) {
                result = combiner.combine(result, it.next());
            }
            return result;
        }
        // Если seq - пустой список:
        return null; // Или возбудить исключение
    }

    // Получает объект функции и вызывает его для каждого объекта
    // в списке, игнорируя возвращаемое значение. Объект
    // функции может действовать как параметр-накопитель,
    // поэтому он возвращается в конце.
    public static <T> Collector<T> forEach(Iterable<T> seq, Collector<T> func) {
        for (T t : seq) {
            func.function(t);
        }
        return func;
    }

    // Создает список результатов, вызывая объект функции
    // для каждого объекта в списке:
    public static <R, T> List<R> transform(Iterable<T> seq, UnaryFunction<R, T> func) {
        List<R> result = new ArrayList<R>();
        for (T t : seq) {
            result.add(func.function(t));
        }
        return result;
    }

    // Применяет унарный предикат к каждому элементу последовательности 
    // и возвращает список элементов, для которых 
    // было получено значение "true": 
    public static <T> List<T> filter(Iterable<T> seq, UnaryPredicate<T> pred) {
        List<T> result = new ArrayList<T>();
        for (T t : seq) {
            if (pred.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    // Для использования приведенных выше обобщенных методов 
    // необходимо создать объекты функций для их адаптации 
    // к нашим конкретным потребностям:
    static class IntegerAdder implements Combiner<Integer> {
        @Override
        public Integer combine(Integer x, Integer y) {
            return x + y;
        }
    }

    static class IntegerSubtracter implements Combiner<Integer> {
        @Override
        public Integer combine(Integer x, Integer y) {
            return x - y;
        }
    }

    static class BigDecimalAdder implements Combiner<BigDecimal> {
        @Override
        public BigDecimal combine(BigDecimal x, BigDecimal y) {
            return x.add(y);
        }
    }

    static class BigIntegerAdder implements Combiner<BigInteger> {
        @Override
        public BigInteger combine(BigInteger x, BigInteger y) {
            return x.add(y);
        }
    }

    static class AtomicLongAdder implements Combiner<AtomicLong> {
        @Override
        public AtomicLong combine(AtomicLong x, AtomicLong y) {
            // Неясно, насколько sto осмысленно: 
            return new AtomicLong(x.addAndGet(y.get()));
        }
    }

    static class BigDecimalUlp implements UnaryFunction<BigDecimal, BigDecimal> {
        @Override
        public BigDecimal function(BigDecimal x) {
            return x.ulp();
        }
    }

    static class GreaterThan<T extends Comparable<T>> implements UnaryPredicate<T> {
        private final T bound;

        public GreaterThan(T bound) {
            this.bound = bound;
        }

        @Override
        public boolean test(T x) {
            return x.compareTo(bound) > 0;
        }
    }

    static class MultiplyinglntegerCollector implements Collector<Integer> {
        private Integer val = 1;

        @Override
        public Integer function(Integer x) {
            val *= x;
            return val;
        }

        @Override
        public Integer result() {
            return val;
        }
    }

    public static void main(String[] args) {
        // Обобщения, списки аргументов переменной длины 
        // и упаковка работают совместно:
        List<Integer> li = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        Integer result = reduce(li, new IntegerAdder());
        print(result);

        result = reduce(li, new IntegerSubtracter());
        print(result);

        print(filter(li, new GreaterThan<Integer>(4)));

        print(forEach(li, new MultiplyinglntegerCollector()).result());

        print(forEach(filter(li, new GreaterThan<Integer>(4)), new MultiplyinglntegerCollector()).
            result());

        MathContext mc = new MathContext(7);
        List<BigDecimal> lbd = Arrays.asList(
            new BigDecimal(1.1, mc), new BigDecimal(2.2, mc),
            new BigDecimal(3.3, mc), new BigDecimal(4.4, mc));

        BigDecimal rbd = reduce(lbd, new BigDecimalAdder());
        print(rbd);
        print(filter(lbd, new GreaterThan<BigDecimal>(new BigDecimal(3))));

        // использование средств генерирования простых чисел BigInteger: 
        List<BigInteger> lbi = new ArrayList<BigInteger>();
        BigInteger bi = BigInteger.valueOf(11);
        for (int i = 0; i < 11; ++i) {
            lbi.add(bi);
            bi = bi.nextProbablePrime();
        }
        print(bi);

        BigInteger rbi = reduce(lbi, new BigIntegerAdder());
        print(rbi);

        // Сумма этого списка простых чисел также является простым числом: 
        print(rbi.isProbablePrime(5));
        List<AtomicLong> lal = Arrays.asList(
            new AtomicLong(11), new AtomicLong(47),
            new AtomicLong(74), new AtomicLong(133));
        AtomicLong ral = reduce(lal, new AtomicLongAdder());
        print(ral);
        print(transform(lbd, new BigDecimalUlp()));
    }
}

/* Output:
28
-26
[5, 6, 7] SO40
5040
11.000000
[3.300000, 4.400000]
[11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47]
311
true
265
[0.000001, 0.000001, 0.000001, 0.000061)
///*/
