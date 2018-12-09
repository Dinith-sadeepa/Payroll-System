package lk.ijse.payroll.entity;

import java.sql.Date;

public class DesignationDetails {
    private int desDetId;
    private int empId;
    private int desId;
    private Date dateSince;

    public DesignationDetails() {
    }

    public DesignationDetails(int desDetId, int empId, int desId, Date dateSince) {
        this.desDetId = desDetId;
        this.empId = empId;
        this.desId = desId;
        this.dateSince = dateSince;
    }

    public int getDesDetId() {
        return desDetId;
    }

    public void setDesDetId(int desDetId) {
        this.desDetId = desDetId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getDesId() {
        return desId;
    }

    public void setDesId(int desId) {
        this.desId = desId;
    }

    public Date getDateSince() {
        return dateSince;
    }

    public void setDateSince(Date dateSince) {
        this.dateSince = dateSince;
    }
}
