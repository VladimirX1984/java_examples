/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typeinfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 *
 * @author Vladimir Динамические посредники «Посредник» (proxy) принадлежит к числу основных паттернов проектирования
 */
class DynamicProxyHandler implements InvocationHandler {
    private final Object proxied;

    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable {
        System.out.
            println("**** proxy. " + proxy.getClass() + ", method- " + method + ", args " + args);
        if (args != null) {
            for (Object arg : args) {
                System.out.println(" " + arg);
            }
        }

        return method.invoke(proxied, args);
    }
}
