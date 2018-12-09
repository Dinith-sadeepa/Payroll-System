package lk.ijse.payroll.dao.custom;

import lk.ijse.payroll.dao.CrudDAO;
import lk.ijse.payroll.entity.DesignationDetails;

public interface DesigantionDetailDAO extends CrudDAO<DesignationDetails,Integer> {
    DesignationDetails searchByEmpId(Integer integer) throws Exception;
}
