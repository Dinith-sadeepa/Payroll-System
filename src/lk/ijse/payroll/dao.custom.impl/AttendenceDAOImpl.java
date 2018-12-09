package lk.ijse.payroll.dao.custom.impl;

import lk.ijse.payroll.dao.CrudUtil;
import lk.ijse.payroll.dao.custom.AttendenceDAO;
import lk.ijse.payroll.entity.Attendence;
import lk.ijse.payroll.entity.Employee;

import java.sql.ResultSet;
import java.util.ArrayList;

public class AttendenceDAOImpl implements AttendenceDAO {
    @Override
    public boolean save(Attendence entity) throws Exception {
        return CrudUtil.executeUpdate("call addAttendanceIN(?,?)", entity.getDesDetId(), entity.getStatus()) > 0;
    }

    @Override
    public boolean saveLeave(Attendence entity) throws Exception {
        return CrudUtil.executeUpdate("call addAttendanceOUT(?)", entity.getDesDetId()) > 0;
    }

    @Override
    public boolean update(Attendence entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Attendence search(Integer integer) throws Exception {
        return null;
    }

    @Override
    public Attendence searchByDesDetId(Integer desDetId) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("call searchAttendance(?)", desDetId);
        if (rst.next()) {
            return new Attendence(rst.getInt(1), rst.getInt(2), rst.getDate(3), rst.getString(4), rst.getTime(5), rst.getTime(6), rst.getTime(7));
        } else {
            return null;
        }
    }


    @Override
    public ArrayList<Attendence> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery("call getAllAttendance()");
        ArrayList<Attendence> attendenceArrayList = new ArrayList<>();
        while (resultSet.next()){

            attendenceArrayList.add(new Attendence(resultSet.getInt(1),resultSet.getInt(2),resultSet.getDate(3),resultSet.getString(4),resultSet.getTime(5),resultSet.getTime(6),resultSet.getTime(7)));
        }
        return attendenceArrayList;
    }
}
