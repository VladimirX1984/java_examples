/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generics;

/**
 *
 * @author Vladimir
 */
// {CompileTimeError} (He компилируется)
interface Payable<T> {
}

class Employee implements Payable<Employee> {
}

/*
Класс Hourly компилироваться не будет, потому что стирание сокращает Payable<Employee> и Payable<Hourly> до Payable,
а в приведенном примере это означало бы двукратную реализацию одного интерфейса. 
Интересная подробность: если удалить параметризованные аргументы из обоих упоминаний Payable, 
как это делает компилятор при стирании, программа откомпилируется.
class Hourly extends Employee implements Payable<Hourly> {
}
*/
