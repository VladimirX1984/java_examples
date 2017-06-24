/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generics;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import static net.mindview.util.Tuple.tuple;
import net.mindview.util.TwoTuple;

/**
 *
 * @author Vladimir - Динамически заместитель позволяет создать механизм, который
 * более точно моделирует примеси, чем паттерн "Декоратор"
 */
interface IBasicImp extends Generic<String> {

}

class BasicImp extends Basic implements IBasicImp {
    public BasicImp() {
        super();
    }
}

interface ITimeStampedImp extends IBasicImp {
    long getStamp();
}

interface ISerialNumberedImp extends IBasicImp {
    long getSerialNumber();
}

class TimeStampedImp extends TimeStamped implements ITimeStampedImp {
    public TimeStampedImp() {
        super(new BasicImp());
    }
}

class SerialNumberedImp extends SerialNumbered implements ISerialNumberedImp {
    public SerialNumberedImp() {
        super(new BasicImp());
    }
}

class InterfaceGetter {
    public static Class<?> getFirstInterface(Class<?> classObject, boolean isSuper) {
        return getInterfaceIn(classObject, isSuper);
    }

    private static Class<?> getInterfaceIn(Class<?> classObject, boolean isSuper) {
        Class[] interfaces = classObject.getInterfaces();
        if (interfaces.length > 0) {
            return interfaces[0];
        }
        if (!isSuper) {
            return null;
        }
        return getInterfaceIn(classObject.getSuperclass(), isSuper);
    }
}

public class DynamicProxyMixin {
    public static void main(String[] args) {
        Object mixin = MixinProxy.newInstance(
            tuple(new BasicImp(), InterfaceGetter.getFirstInterface(Basic.class, false)),
            tuple(new TimeStampedImp(), InterfaceGetter.
                getFirstInterface(TimeStampedImp.class, false)),
            tuple(new SerialNumberedImp(), InterfaceGetter.
                getFirstInterface(SerialNumberedImp.class, false)));
        IBasicImp b = (IBasicImp)mixin;
        ITimeStampedImp t = (ITimeStampedImp)mixin;
        ISerialNumberedImp s = (ISerialNumberedImp)mixin;
        s.set("Hello");
        System.out.println(b.get());
        System.out.println(t.getStamp());
        System.out.println(s.getSerialNumber());
    }
}
