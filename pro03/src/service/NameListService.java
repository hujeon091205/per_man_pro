package service;

import domain.*;
import service.TeamException;

import static service.Data.*;

public class NameListService {
    private Employee[] employees;
    public NameListService(){
        employees=new Employee[EMPLOYEES.length];
        for(int i=0;i<employees.length;i++){
            int type=Integer.parseInt(EMPLOYEES[i][0]);
            int id=Integer.parseInt(EMPLOYEES[i][1]);
            String name=EMPLOYEES[i][2];
            int age=Integer.parseInt(EMPLOYEES[i][3]);
            double salary=Double.parseDouble(EMPLOYEES[i][4]);
            double bonus;
            int stock;
            Equipment equipment;
            switch (type){
                case EMPLOYEE:
                    employees[i]=new Employee(id, name, age, salary);
                    break;
                case PROGRAMMER:
                    equipment=createEquipment(i);
                    employees[i]=new Programmer(id, name, age, salary,equipment);
                    break;
                case DESIGNER:
                    equipment=createEquipment(i);
                    bonus=Double.parseDouble(EMPLOYEES[i][5]);
                    employees[i]=new Designer(id, name, age, salary,equipment,bonus);
                case ARCHITECT:
                    equipment=createEquipment(i);
                    bonus=Double.parseDouble(EMPLOYEES[i][5]);
                    stock=Integer.parseInt(EMPLOYEES[i][6]);
                    employees[i]=new Architect(id, name, age, salary,equipment,bonus,stock);
            }

}

            

        }
    private Equipment createEquipment(int index){
        int type=Integer.parseInt(EQIPMENTS[index][0]);
        String model=EQIPMENTS[index][1];
        String info=EQIPMENTS[index][2];
        switch (type){
            case 21:
                return new PC(model, info);
            case 22:
                int price=Integer.parseInt(info);
                return new Notebook(model,price);
            case 23:
                String name=info;
                return new Printer(model,name);
        }
        return null;
    }
    public Employee[] getAllEmployees(){
        return employees;
    }
    public Employee getEmployee(int id) throws TeamException {

        for(int i = 0;i < employees.length;i++){
            if(employees[i].getId() == id){
                return employees[i];
            }
        }
        throw new TeamException("找不到指定的员工");
    }



}
