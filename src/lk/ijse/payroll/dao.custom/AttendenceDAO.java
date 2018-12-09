package lk.ijse.payroll.dao.custom;

import lk.ijse.payroll.dao.CrudDAO;
import lk.ijse.payroll.entity.Attendence;

public interface AttendenceDAO extends CrudDAO<Attendence,Integer> {
    boolean saveLeave(Attendence entity) throws Exception;

    Attendence searchByDesDetId(Integer desDetId) throws Exception;
}
