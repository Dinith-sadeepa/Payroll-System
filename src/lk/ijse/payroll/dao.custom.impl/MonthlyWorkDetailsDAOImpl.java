package lk.ijse.payroll.dao.custom.impl;

import lk.ijse.payroll.dao.CrudUtil;
import lk.ijse.payroll.dao.custom.MonthlyWorkDetailsDAO;
import lk.ijse.payroll.entity.MonthlyWorkDetails;

import java.sql.ResultSet;
import java.util.ArrayList;

public class MonthlyWorkDetailsDAOImpl implements MonthlyWorkDetailsDAO {

    @Override
    public boolean save(MonthlyWorkDetails entity) throws Exception {
        return CrudUtil.executeUpdate("call addMonthlyWorkedDetails(?,?)", entity.getDesDetId(), entity.getDayMustWork()) > 0;
    }

    @Override
    public boolean update(MonthlyWorkDetails entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Integer integer) throws Exception {
        return false;
    }

    @Override
    public MonthlyWorkDetails search(Integer id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("call searchMonthlyWorkDetails(?)", id);
        if(rst.next()){
            return new MonthlyWorkDetails(rst.getInt(1),rst.getInt(2),rst.getDate(3),rst.getString(4),rst.getInt(5),rst.getInt(6),rst.getInt(7));
        }else{
            return null;
        }
    }

    @Override
    public ArrayList<MonthlyWorkDetails> getAll() throws Exception {
        return null;
    }
}
