/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jxapp;

import net.mindview.util.Print;

/**
 *
 * @author Vladimir Регистрация фабрик
 */
public class RegisteredFactories {

    public static void main(String[] args) {
        Print.print("RegisteredFactories");
        for (int i = 0; i < 10; i++) {
            System.out.println(Part.createRandom());
        }
    }
}
