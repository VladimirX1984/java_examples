/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typeinfo;

import java.lang.reflect.Proxy;
import static net.mindview.util.Print.print;

/**
 *
 * @author Vladimir
 */
public class SimpleDynamicProxy {
    public static void consumer(Interface іface) {
        іface.doSomething();
        іface.somethingElse("bonobo");
    }

    public static void main(String[] args) {
        print("SimpleDynamicProxy");
        RealObject real = new RealObject();
        consumer(real);
        // Вставляем посредника и вызываем снова: 
        Interface proxy = (Interface)Proxy.newProxyInstance(
            Interface.class.getClassLoader(),
            new Class[]{Interface.class},
            new DynamicProxyHandler(real));
        consumer(proxy);
    }
}
