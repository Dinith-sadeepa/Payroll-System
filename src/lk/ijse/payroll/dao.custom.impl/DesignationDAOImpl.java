package lk.ijse.payroll.dao.custom.impl;

import lk.ijse.payroll.dao.CrudUtil;
import lk.ijse.payroll.dao.custom.DesignationDAO;
import lk.ijse.payroll.entity.Designation;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DesignationDAOImpl implements DesignationDAO {
    @Override
    public boolean save(Designation des) throws Exception {
        return CrudUtil.executeUpdate("call addDesignation(?,?)", des.getDesDescription(),des.getDesBasicSalary())>0;
    }

    @Override
    public boolean update(Designation des) throws Exception {
        return CrudUtil.executeUpdate("call updateDesignation(?,?)",des.getDesDescription(),des.getDesBasicSalary())>0;
    }

    @Override
    public boolean delete(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Designation search(Integer integer) throws Exception {
        return null;
    }

    @Override
    public Designation searchByDescription(String desc) throws Exception{
        ResultSet resultSet = CrudUtil.executeQuery("call searchDesignation(?)", desc);
        if(resultSet.next()){
            return new Designation(resultSet.getInt(1),resultSet.getString(2),resultSet.getDouble(3));
        }else{
            return null;
        }
    }

    @Override
    public Designation searchByEmpId(Integer eId) throws Exception{
        ResultSet resultSet = CrudUtil.executeQuery("call getEmployeeDesignation(?)", eId);
        if(resultSet.next()){
            return new Designation(resultSet.getInt(1),resultSet.getString(2),resultSet.getDouble(3));
        }else{
            return null;
        }
    }

    @Override
    public ArrayList<Designation> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery("call viewDesignation()");
        ArrayList<Designation> designations = new ArrayList<>();
        while (resultSet.next()){
            designations.add(new Designation(resultSet.getInt(1),resultSet.getString(2),resultSet.getDouble(3)));
        }
        return designations;
    }
}
