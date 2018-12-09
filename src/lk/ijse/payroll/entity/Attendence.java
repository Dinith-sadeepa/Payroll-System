package lk.ijse.payroll.entity;

import java.sql.Date;
import java.sql.Time;

public class Attendence {
    private int attId;
    private int desDetId;
    private Date attDate;
    private String status;
    private Time inTime;
    private Time outTime;
    private Time otHours;
    private Employee employee;

    public Attendence() {
    }

    public Attendence(int attId, int desDetId, Date attDate, String status, Time inTime, Time outTime, Time otHours) {
        this.attId = attId;
        this.desDetId = desDetId;
        this.attDate = attDate;
        this.status = status;
        this.inTime = inTime;
        this.outTime = outTime;
        this.otHours = otHours;
    }

    public Attendence(int desDetId, Date attDate, String status, Time inTime, Time outTime, Time otHours) {
        this.desDetId = desDetId;
        this.attDate = attDate;
        this.status = status;
        this.inTime = inTime;
        this.outTime = outTime;
        this.otHours = otHours;
    }

    public Attendence(int desDetId, String status) {
        this.desDetId = desDetId;
        this.status = status;
    }

    public Attendence(Date attDate, String status, Time inTime, Time outTime, Time otHours, Employee employee) {
        this.attDate = attDate;
        this.status = status;
        this.inTime = inTime;
        this.outTime = outTime;
        this.otHours = otHours;
        this.employee = employee;
    }

    public int getAttId() {
        return attId;
    }

    public void setAttId(int attId) {
        this.attId = attId;
    }

    public int getDesDetId() {
        return desDetId;
    }

    public void setDesDetId(int desDetId) {
        this.desDetId = desDetId;
    }

    public Date getAttDate() {
        return attDate;
    }

    public void setAttDate(Date attDate) {
        this.attDate = attDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Time getInTime() {
        return inTime;
    }

    public void setInTime(Time inTime) {
        this.inTime = inTime;
    }

    public Time getOutTime() {
        return outTime;
    }

    public void setOutTime(Time outTime) {
        this.outTime = outTime;
    }

    public Time getOtHours() {
        return otHours;
    }

    public void setOtHours(Time otHours) {
        this.otHours = otHours;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
