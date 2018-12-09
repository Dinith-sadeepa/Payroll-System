package lk.ijse.payroll.dao;

import lk.ijse.payroll.dao.custom.impl.*;

public class DAOFactory {
    public enum DAOTypes {
        EMPLOYEE, ALLOWENCES, DESIGNATION, SALARY_ADVANCED, DESIGNATION_DETAIL, ATTENDENCE, CONFIG_TABLE, MONTHLY_WORK_DETAILS,MONTHLYFINALSALARY;
    }

    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case EMPLOYEE:
                return (T) new EmployeeDAOImpl();
            case ALLOWENCES:
                return (T) new AllowencesDAOImpl();
            case ATTENDENCE:
                return (T) new AttendenceDAOImpl();
            case SALARY_ADVANCED:
                return (T) new SalaryAdvancedDAOImpl();
            case CONFIG_TABLE:
                return (T) new ConfigTableDAOImpl();
            case DESIGNATION:
                return (T) new DesignationDAOImpl();
            case MONTHLY_WORK_DETAILS:
                return (T) new MonthlyWorkDetailsDAOImpl();
            case DESIGNATION_DETAIL:
                return (T) new DesignationDetailsDAOImpl();
            case MONTHLYFINALSALARY:
                return (T) new MonthlyFinalSalaryDAOImpl();
            default:
                return null;
        }
    }
}
