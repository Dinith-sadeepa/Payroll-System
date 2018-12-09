package lk.ijse.payroll.dao.custom.impl;

import lk.ijse.payroll.dao.CrudUtil;
import lk.ijse.payroll.dao.custom.AllowencesDAO;
import lk.ijse.payroll.entity.Allowences;

import java.sql.ResultSet;
import java.util.ArrayList;

public class AllowencesDAOImpl implements AllowencesDAO {
    @Override
    public boolean save(Allowences entity) throws Exception {
        return CrudUtil.executeUpdate("call addAllowences(?,?)",entity.getAllowencesDescription(),entity.getAllowencesRate())>0;
    }

    @Override
    public boolean update(Allowences entity) throws Exception {
        return CrudUtil.executeUpdate("call updateAllowences(?,?)",entity.getAllowencesDescription(),entity.getAllowencesRate())>0;
    }

    @Override
    public boolean delete(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Allowences search(Integer integer) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Allowences> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery("call viewAllowences()");
        ArrayList<Allowences> allowences = new ArrayList<>();
        while (resultSet.next()){
            allowences.add(new Allowences(resultSet.getString(1),resultSet.getInt(2)));
        }
        return allowences;
    }
}
