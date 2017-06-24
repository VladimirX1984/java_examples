/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mindview.util;

import java.io.PrintStream;

/**
 *
 * @author Vladimir
 */
public class Print {

    // Печать с переводом строки:
    public static void print(Object obj) {
        System.out.println(obj);
    }

    // Перевод строки:
    public static void print() {
        System.out.println();
    }

    // Печать без перевода строки:
    public static void printnb(Object obj) {
        System.out.print(obj);
    }

    // Новая конструкция Java SE5 printf() (from C):
    public static PrintStream printf(String format, Object... args) {
        return System.out.printf(format, args);
    }
}
