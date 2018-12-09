package lk.ijse.payroll.entity;

import java.sql.Date;

public class MonthlyWorkDetails {
    private int mSId;
    private int desDetId;
    private Date getDate;
    private String toMonth;
    private int dayMustWork;
    private int workedDays;
    private int totalOTHours;

    public MonthlyWorkDetails() {
    }

    public MonthlyWorkDetails(int mSId, int desDetId, Date getDate, String toMonth, int dayMustWork, int workedDays, int totalOTHours) {
        this.mSId = mSId;
        this.desDetId = desDetId;
        this.getDate = getDate;
        this.toMonth = toMonth;
        this.dayMustWork = dayMustWork;
        this.workedDays = workedDays;
        this.totalOTHours = totalOTHours;
    }

    public MonthlyWorkDetails(int desDetId, Date getDate, String toMonth, int dayMustWork, int workedDays, int totalOTHours) {
        this.desDetId = desDetId;
        this.getDate = getDate;
        this.toMonth = toMonth;
        this.dayMustWork = dayMustWork;
        this.workedDays = workedDays;
        this.totalOTHours = totalOTHours;
    }

    public MonthlyWorkDetails(int desDetId, int dayMustWork) {
        this.desDetId = desDetId;
        this.dayMustWork = dayMustWork;
    }

    public int getmSId() {
        return mSId;
    }

    public void setmSId(int mSId) {
        this.mSId = mSId;
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

    public String getToMonth() {
        return toMonth;
    }

    public void setToMonth(String toMonth) {
        this.toMonth = toMonth;
    }

    public int getDayMustWork() {
        return dayMustWork;
    }

    public void setDayMustWork(int dayMustWork) {
        this.dayMustWork = dayMustWork;
    }

    public int getWorkedDays() {
        return workedDays;
    }

    public void setWorkedDays(int workedDays) {
        this.workedDays = workedDays;
    }

    public int getTotalOTHours() {
        return totalOTHours;
    }

    public void setTotalOTHours(int totalOTHours) {
        this.totalOTHours = totalOTHours;
    }
}
