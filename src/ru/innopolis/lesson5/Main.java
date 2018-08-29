package ru.innopolis.lesson5;

import ru.innopolis.lesson5.box.BoxIO;

import java.io.IOException;

public class Main {
    public static final String path = "C:\\Test\\Employee.txt";
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        BoxIO boxIO = new BoxIO();

        Employee emp1 = new Employee("Vasya",18,34000,"Operator");
        Employee emp2 = new Employee("Ivan",35,60000,"Manager");
        Employee emp3 = new Employee("Kolya",30,60000,"Manager");
        Employee emp4 = new Employee("Vova",23,90000,"Direktor");

        boxIO.save(emp1);
        boxIO.save(emp2);
        boxIO.save(emp3);
        boxIO.save(emp4);

        System.out.println(boxIO.transformationInCollection(null));

        System.out.println(boxIO.getByName("Vasya"));
        System.out.println(boxIO.getByJob("Manager"));

        emp2.setSalary(55000);
        boxIO.saveOrUpdate(emp2);

        boxIO.changeAllWork("Manager", "Expert");

        System.out.println(boxIO.transformationInCollection(null));




    }



}
