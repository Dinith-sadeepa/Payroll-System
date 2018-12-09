package lk.ijse.payroll.dao.custom.impl;

import lk.ijse.payroll.dao.CrudUtil;
import lk.ijse.payroll.dao.custom.ConfigTableDAO;
import lk.ijse.payroll.entity.ConfigTable;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ConfigTableDAOImpl implements ConfigTableDAO {
    @Override
    public boolean save(ConfigTable entity) throws Exception {
        return CrudUtil.executeUpdate("call addConfig(?,?)",entity.getConfigDescription(),entity.getConfigRate())>0;
    }

    @Override
    public boolean update(ConfigTable entity) throws Exception {
        return CrudUtil.executeUpdate("call updateConfig(?,?)",entity.getConfigDescription(),entity.getConfigRate())>0;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public ConfigTable search(String s) throws Exception {
        return null;
    }

    @Override
    public ArrayList<ConfigTable> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery("call viewConfig()");
        ArrayList<ConfigTable>  configTables = new ArrayList<>();
        while (resultSet.next()){
            configTables.add(new ConfigTable(resultSet.getString(1),resultSet.getInt(2)));
        }
        return configTables;
    }
}
