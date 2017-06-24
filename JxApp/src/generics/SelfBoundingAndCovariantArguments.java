/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generics;

/**
 *
 * @author Vladimir - с самоограничиваемыми типами в производном классе содержится только один метод,
 * в аргументе которого передается производный, а не базовый тип
 */
interface SelfBoundSetter<T extends SelfBoundSetter<T>> {
    void set(T arg);
}

interface Setter extends SelfBoundSetter<Setter> {

}

class SetterIn implements Setter {
    private Setter obj;
    
    @Override
    public void set(Setter obj) {
        this.obj = obj;
    }
}

class SelfBoundSetterIn implements SelfBoundSetter<Setter> {
    private Setter obj;
    
    @Override
    public void set(Setter obj) {
        this.obj = obj;
    }
}

public class SelfBoundingAndCovariantArguments {
    void testA(Setter s1, Setter s2, SelfBoundSetter sbs) {
        s1.set(s2);
        //s1.set(sbs); ошибка
    }

    void testB(Setter s1, Setter s2, SelfBoundSetter<?> sbs) {
        s1.set(s2);
        //s1.set(sbs); ошибка    
    }

    void testC(Setter s1, Setter s2, SelfBoundSetter<Setter> sbs) {
        s1.set(s2);
        //s1.set(sbs); ошибка    
    }
    
    public static void main(String[] args) {
        SelfBoundingAndCovariantArguments obj = new SelfBoundingAndCovariantArguments();
        obj.testA(new SetterIn(), new SetterIn(), new SelfBoundSetterIn());
        obj.testB(new SetterIn(), new SetterIn(), new SelfBoundSetterIn());
        obj.testC(new SetterIn(), new SetterIn(), new SelfBoundSetterIn());
        obj.testA(new SetterIn(), new SetterIn(), new SetterIn());
        obj.testB(new SetterIn(), new SetterIn(), new SetterIn());
        obj.testC(new SetterIn(), new SetterIn(), new SetterIn());
    }
}
