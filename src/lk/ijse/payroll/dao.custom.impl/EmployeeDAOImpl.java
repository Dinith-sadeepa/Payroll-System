package lk.ijse.payroll.dao.custom.impl;

import lk.ijse.payroll.dao.CrudUtil;
import lk.ijse.payroll.dao.custom.EmployeeDAO;
import lk.ijse.payroll.entity.Employee;

import java.sql.ResultSet;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public boolean save(Employee employee) throws Exception {
        return CrudUtil.executeUpdate("call addEmployee(?,?,?,?,?) ",employee.getEmpId(),employee.getEmpName(),employee.getEmpNIC(),employee.getEmpAddress(),employee.getDesignationDetails().getDesId() )>0;
    }

    @Override
    public boolean update(Employee employee) throws Exception {
        return CrudUtil.executeUpdate("call updateEmployee(?,?,?,?,?) ",employee.getEmpId(),employee.getEmpName(),employee.getEmpNIC(),employee.getEmpAddress(),employee.getDesignationDetails().getDesId() )>0;
    }

    @Override
    public boolean delete(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Employee search(Integer eId) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("call searchEmployee(?)", eId);
        if(rst.next()){
            return new Employee(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4));
        }else{
            return null;
        }
    }

    @Override
    public Employee searchByName(String name) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("call searchEmployeeByName(?)", name);
        if(rst.next()){
            return new Employee(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4));
        }else{
            return null;
        }
    }

    @Override
    public ArrayList<Employee> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("call getAllEmployee()");
        ArrayList<Employee> employees = new ArrayList<>();
        while (rst.next()) {
            employees.add(new Employee(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5)));
        }
        return employees;
    }

    @Override
    public ArrayList<Employee> getAllDetails() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("call getAllEmployeeDetails()");
        ArrayList<Employee> employees = new ArrayList<>();
        while (rst.next()) {

        }
        return employees;
    }
}
