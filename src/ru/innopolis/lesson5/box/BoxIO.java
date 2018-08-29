package ru.innopolis.lesson5.box;

import ru.innopolis.lesson5.Employee;
import ru.innopolis.lesson5.Main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BoxIO {

    public boolean save(Employee employee) {

        if (employee != null) {

            List<Employee> listThereIsEmp;
            try {
                listThereIsEmp = transformationInCollection(null);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
                listThereIsEmp = new ArrayList<>();
            }
            listThereIsEmp.add(employee);
            return addInFile(listThereIsEmp);

        } else {
            return false;
        }
    }

    public boolean delete(Employee employee) {

        if (employee != null) {
            List<Employee> listThereIsEmp;
            try {
                listThereIsEmp = transformationInCollection(employee);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
                listThereIsEmp = new ArrayList<>();
            }
            return addInFile(listThereIsEmp);

        } else {
            return false;
        }
    }


    private boolean addInFile (List<Employee> list){

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(Main.path)))){
            Double salary = 0.0;

            for (Employee emp: list) {
                salary += emp.getSalary();
                outputStream.writeObject(emp);
            }
            outputStream.writeDouble(salary);
            return true;

        } catch (IOException e) { ;
            return false;
        }

    }

    public Employee getByName(String name){

        List<Employee> listThereIsEmp;

        try {
            listThereIsEmp = transformationInCollection(null);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            listThereIsEmp = new ArrayList<>();
        }

        for (Employee auditedEmployee:listThereIsEmp) {
            if (auditedEmployee.getName().equals(name)){
                return auditedEmployee;
            }
        }

        return  null;
    }

    public List<Employee> getByJob(String job){

        List<Employee> resultList = new ArrayList<>();
        List<Employee> listThereIsEmp;
        try {
            listThereIsEmp = transformationInCollection(null);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            listThereIsEmp = new ArrayList<>();
        }

        for (Employee auditedEmployee:listThereIsEmp) {
            if (auditedEmployee.getJob().equals(job)){
                resultList.add(auditedEmployee);
            }
        }

        return  resultList;

    }

    public boolean saveOrUpdate (Employee employee){
        boolean hasEmpl = false;

        List<Employee> listThereIsEmp;
        try {
            listThereIsEmp = transformationInCollection(null);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            listThereIsEmp = new ArrayList<>();
        }

        for (int i = 0; i <  listThereIsEmp.size(); i++) {
            if (listThereIsEmp.get(i).equals(employee)){
                listThereIsEmp.set(i,employee);
                hasEmpl = true;
            }
        }

        if (!hasEmpl){
            listThereIsEmp.add(employee);
        }

        return addInFile(listThereIsEmp);

    }


    public boolean changeAllWork(String oldJob, String newJob){
        boolean hasChange = false;

        List<Employee> listThereIsEmp;
        try {
            listThereIsEmp = transformationInCollection(null);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            listThereIsEmp = new ArrayList<>();
        }

        for (Employee auditedEmployee:listThereIsEmp) {
            if (auditedEmployee.getJob().equals(oldJob)){
                auditedEmployee.setJob(newJob);
                hasChange = true;
            }
        }

        if (hasChange){
            return addInFile(listThereIsEmp);
        }

        return hasChange;
    }


    public List<Employee> transformationInCollection (Employee employeeForDel) throws IOException, ClassNotFoundException {
        Object employee;
        boolean delEmp;
        List<Employee> list = new ArrayList<>();
        File file = new File (Main.path);

        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(Main.path))) {
                while ((employee = inputStream.readObject()) != null) {
                    try {
                        if (employeeForDel != null) {
                            delEmp = employee.equals(employeeForDel);
                        } else {
                            delEmp = false;
                        }
                        if (!delEmp) {
                            list.add((Employee) employee);
                        }

                        if (inputStream.available() == 8) {
                            break;
                        }

                    } catch (ClassCastException e) {
                        throw new ClassCastException("В файле есть объекты, отличные от Employee и Salary");
                    }
                }

            } catch (IOException e) {
                throw new IOException("Ошибка считывания");
            }catch (ClassNotFoundException e){
                throw new ClassNotFoundException("Ошибка считывания");
            }
        }

        return list;

    }

}
