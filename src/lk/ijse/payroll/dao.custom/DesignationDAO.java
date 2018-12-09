package lk.ijse.payroll.dao.custom;

import lk.ijse.payroll.dao.CrudDAO;
import lk.ijse.payroll.entity.Designation;

public interface DesignationDAO extends CrudDAO<Designation,Integer> {
    Designation searchByDescription(String desc) throws Exception;

    Designation searchByEmpId(Integer eId) throws Exception;
}
