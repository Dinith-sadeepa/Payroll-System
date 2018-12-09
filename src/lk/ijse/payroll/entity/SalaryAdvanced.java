package lk.ijse.payroll.entity;

import java.sql.Date;

public class SalaryAdvanced {
    private int saAdId;
    private int desDetId;
    private Date getDate;
    private double amount;

    public SalaryAdvanced() {
    }

    public SalaryAdvanced(int desDetId, double amount) {
        this.desDetId = desDetId;
        this.amount = amount;
    }

    public SalaryAdvanced(int saAdId, int desDetId, Date getDate, double amount) {
        this.saAdId = saAdId;
        this.desDetId = desDetId;
        this.getDate = getDate;
        this.amount = amount;
    }

    public int getSaAdId() {
        return saAdId;
    }

    public void setSaAdId(int saAdId) {
        this.saAdId = saAdId;
    }

    public int getDesDetId() {
        return desDetId;
    }

    public void setDesDetId(int desDetId) {
        this.desDetId = desDetId;
    }

    public Date getGetDate() {
        return getDate;
    }

    public void setGetDate(Date getDate) {
        this.getDate = getDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
