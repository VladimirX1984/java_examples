/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generics;

/**
 *
 * @author Vladimir
 * @param <T>
 */
public class BasicHolder<T> implements Generic<T> {
    private T element;

    public BasicHolder() {
    }

    public BasicHolder(T val) {
        element = val;
    }

    @Override
    public void set(T arg) {
        element = arg;
    }

    @Override
    public T get() {
        return element;
    }

    public void f() {
        System.out.println(element.getClass().getSimpleName());
    }
}
