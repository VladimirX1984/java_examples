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
class Base {
}

class Derived extends Base {
}

interface OrdinaryGetter {
    Base get();
}

interface DerivedGetter extends OrdinaryGetter {
    // Возвращаемый тип переопределенного метода может изменяться:
    @Override
    Derived get();
}

interface Thing {
    Object thing();
}

class CharSequenceThing implements Thing {
    @Override
    public CharSequence thing() {
        return "CharSequence!";
    }
}

class StringThing extends CharSequenceThing {
    @Override
    public String thing() {
        return "String!";
    }
}

public class CovariantReturnTypes {
    void test(DerivedGetter d) {
        Derived d2 = d.get();
    }
}
