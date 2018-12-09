package lk.ijse.payroll.dao.custom;

import lk.ijse.payroll.dao.CrudDAO;
import lk.ijse.payroll.entity.Employee;

import java.util.ArrayList;

public interface EmployeeDAO extends CrudDAO<Employee,Integer> {
    Employee searchByName(String name) throws Exception;

    ArrayList<Employee> getAllDetails() throws Exception;
}
