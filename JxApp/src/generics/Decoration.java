/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generics;

import java.util.Date;

/**
 *
 * @author Vladimir - паттерн "Декоратор" - моделирование примеси. Смотри файл Mixing.cpp
 */

class Basic implements Generic<String> {
    private String value;

    @Override
    public void set(String val) {
        value = val;
    }

    @Override
    public String get() {
        return value;
    }
}

class Decorator extends Basic {
    protected Basic basic;

    public Decorator(Basic basic) {
        this.basic = basic;
    }

    @Override
    public void set(String val) {
        basic.set(val);
    }

    @Override
    public String get() {
        return basic.get();
    }
}

class TimeStamped extends Decorator {
    private final long timeStamp;

    public TimeStamped(Basic basic) {
        super(basic);
        timeStamp = new Date().getTime();
    }

    public long getStamp() {
        return timeStamp;
    }
}

class SerialNumbered extends Decorator {
    private final long serialNumber;
    static long counter;

    public SerialNumbered(Basic basic) {
        super(basic);
        serialNumber = counter++;
    }

    public long getSerialNumber() {
        return serialNumber;
    }
}

public class Decoration {
    public static void main(String[] args) {
        TimeStamped t = new TimeStamped(new Basic());
        t.get();
        t.getStamp();
        TimeStamped t2 = new TimeStamped(new SerialNumbered(new Basic()));
        t2.get();
        t2.getStamp();
        // t2.getSerialNumber() - недоступно
        SerialNumbered s = new SerialNumbered(new Basic());
        s.get();
        s.getSerialNumber();
        SerialNumbered s2 = new SerialNumbered(new TimeStamped(new Basic()));
        s2.get();
        s2.getSerialNumber();
        // s2.getStamp() - недоступно
    }
}
