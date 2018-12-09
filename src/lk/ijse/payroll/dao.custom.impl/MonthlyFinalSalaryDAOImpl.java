package lk.ijse.payroll.dao.custom.impl;

import lk.ijse.payroll.dao.CrudUtil;
import lk.ijse.payroll.dao.custom.MonthlyFinalSalaryDAO;
import lk.ijse.payroll.entity.MonthlyFinalSalary;

import java.sql.ResultSet;
import java.util.ArrayList;

public class MonthlyFinalSalaryDAOImpl implements MonthlyFinalSalaryDAO {
    @Override
    public boolean save(MonthlyFinalSalary entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(MonthlyFinalSalary entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Integer integer) throws Exception {
        return false;
    }

    @Override
    public MonthlyFinalSalary search(Integer id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("call searchMonthlyFinalSalary(?)", id);
        if(rst.next()){
            return new MonthlyFinalSalary(rst.getInt(1),rst.getInt(2),rst.getDouble(3),rst.getDouble(4),rst.getDouble(5),rst.getDouble(6),rst.getDouble(7),rst.getDouble(8),rst.getDouble(9));
        }else{
            return null;
        }
    }

    @Override
    public ArrayList<MonthlyFinalSalary> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("call getAllMonthlyFinalSalary()");
        ArrayList<MonthlyFinalSalary> monthlyFinalSalaryArrayList = new ArrayList<>();
        while (rst.next()) {
            monthlyFinalSalaryArrayList.add(new MonthlyFinalSalary(rst.getInt(1),rst.getInt(2),rst.getDouble(3),rst.getDouble(4),rst.getDouble(5),rst.getDouble(6),rst.getDouble(7),rst.getDouble(8),rst.getDouble(9)));
        }
        return monthlyFinalSalaryArrayList;
    }
}
