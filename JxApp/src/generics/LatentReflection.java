/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generics;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static net.mindview.util.Print.*;

/**
 *
 * @author Vladimir - компенсация отсутсвия латентной типизации
 * Данный способ основан на использовании отражения
 */
class Robot implements Performs {
    @Override
    public void speak() {
        print("Click!");
    }

    @Override
    public void sit() {
        print("Clank!");
    }

    public void oilChange() {
    }
}

class Mime {
    public void walkAgainstTheWind() {
    }

    public void sit() {
        print("Pretending to sit");
    }

    public void pushInvisibleWalls() {
    }

    @Override
    public String toString() {
        return "Mime";
    }
}

// Does not implement Performs:
class SmartDog {
    public void speak() {
        print("Woof!");
    }

    public void sit() {
        print("Sitting");
    }

    public void reproduce() {
    }
}

class CommunicateReflectively {
    public static void perform(Object speaker) {
        Class<?> spkr = speaker.getClass();
        try {
            try {
                Method speak = spkr.getMethod("speak");
                speak.invoke(speaker);
            }
            catch (NoSuchMethodException e) {
                print(speaker + " cannot speak");
            }
            try {
                Method sit = spkr.getMethod("sit");
                sit.invoke(speaker);
            }
            catch (NoSuchMethodException e) {
                print(speaker + " cannot sit");
            }
        }
        catch (IllegalAccessException | IllegalArgumentException | SecurityException | InvocationTargetException e) {
            throw new RuntimeException(speaker.toString(), e);
        }
    }
}

public class LatentReflection {
    public static void main(String[] args) {
        CommunicateReflectively.perform(new SmartDog());
        CommunicateReflectively.perform(new Robot());
        CommunicateReflectively.perform(new Mime());
    }
}

/* Output:
Woof!
Sitting
Click!
Clank!
Mime cannot speak
Pretending to sit
 *///:~
