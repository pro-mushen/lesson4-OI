package ru.innopolis.lesson5;

import java.io.*;
import java.util.Objects;

public class Employee implements Externalizable {
    private String name;
    private int age;
    private double salary;
    private String job;

    public Employee(){

    }

    public Employee(String name, int age, double salary, String job) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.job = job;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(name);
        out.writeInt(age);
        out.writeDouble(salary);
        out.writeUTF(job);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = in.readUTF();
        age = in.readInt();
        salary = in.readDouble();
        job = in.readUTF();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", job='" + job + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age &&
                Objects.equals(name, employee.name) &&
                Objects.equals(job, employee.job);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, salary, job);
    }


}
