/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jxapp;

import generics.*;
import generics.coffee.CoffeeGenerator;
import net.mindview.util.Print;
import typeinfo.*;

/**
 *
 * @author Vladimir
 */
public class JxApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        PetCount.main(args);
        Print.print();
        PetCount2.main(args);
        Print.print();
        PetCount3.main(args);
        Print.print();
        PetCount4.main(args);
        Print.print();
        RegisteredFactories.main(args);
        Print.print();
        FamilyVsExactType.main(args);
        Print.print();
        ShowMethods.main(new String[]{"typeinfo.ShowMethods"});
        Print.print();
        SimpleProxyDemo.main(args);
        Print.print();
        SimpleDynamicProxy.main(args);
        Print.print();
        Print.print();
        Print.print("Generics!!!");
        Print.print();

        CoffeeGenerator.main(args);

        BasicBounds.main(args);
        CaptureConversion.main(args);
        CovariantArrays.main(args);
        CRGWithBasicHolder.main(args);

        EpicBattle.main(args);
        ExplicitTypeSpecification.main(args);
        Fibonacci.main(args);
        Generators.main(args);
        GenericCast.main(args);
        GenericHolder.main(args);
        GenericsAndCovariance.main(args);
        GenericsAndReturnTypes.main(args);
        Holder.main(args);
        InheritBounds.main(args);
        IterableFibonacci.main(args);
        OrdinaryArguments.main(args);

        PlainGenericInheritance.main(args);
        SimplerPets.main(args);
        Print.print();

        SelfBounding.main(args);
        SelfBoundingMethods.main(args);
        SelfBoundingAndCovariantArguments.main(args);

        Print.print();

        UnboundedWildcards1.main(args);
        UnboundedWildcards2.main(args);
        Unconstrained.main(args);
        Wildcards.main(args);

        Print.print();
        Print.print("Dynamic mixing");
        Decoration.main(args);
        DynamicProxyMixin.main(args);

        Print.print();
        Print.print("Latent typing");
        LatentReflection.main(args);
        try {
            ApplyTest.main(args);
        }
        catch (NoSuchMethodException ex) {
            ex.printStackTrace();
        }
        Print.print();
        FillTest.main(args);
        Print.print();
        Functional.main(args);
    }

}
