package lk.ijse.payroll.dao.custom.impl;

import lk.ijse.payroll.dao.CrudUtil;
import lk.ijse.payroll.dao.custom.DesigantionDetailDAO;
import lk.ijse.payroll.entity.DesignationDetails;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DesignationDetailsDAOImpl implements DesigantionDetailDAO {
    @Override
    public boolean save(DesignationDetails entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(DesignationDetails entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Integer integer) throws Exception {
        return false;
    }

    @Override
    public DesignationDetails search(Integer desDetailId) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery("call searchDesignationDetails(?)", desDetailId);
        if(resultSet.next()){
            return new DesignationDetails(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getDate(4));
        }else{
            return null;
        }
    }

    @Override
    public DesignationDetails searchByEmpId(Integer eId) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery("call searchDesignationDetailsByEmp(?)", eId);
        if(resultSet.next()){
            return new DesignationDetails(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getDate(4));
        }else{
            return null;
        }
    }


    @Override
    public ArrayList<DesignationDetails> getAll() throws Exception {
        return null;
    }
}
