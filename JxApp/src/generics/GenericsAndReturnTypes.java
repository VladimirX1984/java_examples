/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generics;

/**
 *
 * @author Vladimir - ковариантные типы аргументов
 */

interface GenericGetter2<T extends GenericGetter2<T>> extends GenericGetter<T> {    
}

interface Getter extends GenericGetter2<Getter> {}

class GetterIn implements Getter {
    private final Getter obj;
    
    public GetterIn() {
        obj = null;
    }
    
    public GetterIn(Getter obj) {
        this.obj = obj;
    }
    
    @Override
    public Getter get() {
        return obj;
    }
}

public class GenericsAndReturnTypes {
    void test(Getter g) {
        Getter result = g.get();
        GenericGetter gg = g.get();
        GenericGetter<?> res = result.get();
    }
    
    public static void main(String[] args) {
        GenericsAndReturnTypes g = new GenericsAndReturnTypes();
        g.test(new GetterIn(new GetterIn()));
    }
}
