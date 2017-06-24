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
import net.mindview.util.TwoTuple;

/**
 *
 * @author Vladimir
 */
public class MixinProxy implements InvocationHandler {
    private Map<String, Object> delegatesByMethod;

    public static boolean isLog = false;

    public MixinProxy(TwoTuple<Object, Class<?>>... pairs) {
        delegatesByMethod = new HashMap<String, Object>();
        for (TwoTuple<Object, Class<?>> pair : pairs) {
            for (Method method : pair.second.getMethods()) {
                String methodName = method.getName();
                if (!delegatesByMethod.containsKey(methodName)) {
                    delegatesByMethod.put(methodName, pair.first);
                }
            }
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (isLog) {
            System.out.
                println("**** proxy. " + proxy.getClass() + ", method- " + method + ", args " + args);
        }
        String methodName = method.getName();
        Object delegate = delegatesByMethod.get(methodName);
        return method.invoke(delegate, args);
    }
    
    @SuppressWarnings("unchecked")
    public static Object newInstance(TwoTuple<Object, Class<?>>... pairs) {
        if (pairs.length == 0) {
            return null;
        }
        Class[] interfaces = new Class[pairs.length];
        for (int i = 0; i < pairs.length; ++i) {
            interfaces[i] = pairs[i].second;
        }
        ClassLoader cl = pairs[0].first.getClass().getClassLoader();
        return Proxy.newProxyInstance(cl, interfaces, new MixinProxy(pairs));
    }        
}
