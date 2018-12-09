package lk.ijse.payroll.dao.custom.impl;

import lk.ijse.payroll.dao.CrudUtil;
import lk.ijse.payroll.dao.custom.SalaryAdvancedDAO;
import lk.ijse.payroll.entity.SalaryAdvanced;

import java.util.ArrayList;

public class SalaryAdvancedDAOImpl implements SalaryAdvancedDAO {
    @Override
    public boolean save(SalaryAdvanced sa) throws Exception {
        return CrudUtil.executeUpdate("call addSalaryAdvanced(?,?)" , sa.getDesDetId() , sa.getAmount())>0;
    }

    @Override
    public boolean update(SalaryAdvanced entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Integer integer) throws Exception {
        return false;
    }

    @Override
    public SalaryAdvanced search(Integer integer) throws Exception {
        return null;
    }

    @Override
    public ArrayList<SalaryAdvanced> getAll() throws Exception {
        return null;
    }
}
