package lk.ijse.payroll.entity;

public class MonthlyFinalSalary {
    private int monthlyFinalID;
    private int monthlyWorkID;
    private double totalAllowencesRate;
    private double noPay;
    private double otPay;
    private double salaryAdvanced;
    private double EPFPay;
    private double ETFPay;
    private double netSalary;

    public MonthlyFinalSalary() {
    }

    public MonthlyFinalSalary(int monthlyFinalID, int monthlyWorkID, double totalAllowencesRate, double noPay, double otPay, double salaryAdvanced, double EPFPay, double ETFPay, double netSalary) {
        this.monthlyFinalID = monthlyFinalID;
        this.monthlyWorkID = monthlyWorkID;
        this.totalAllowencesRate = totalAllowencesRate;
        this.noPay = noPay;
        this.otPay = otPay;
        this.salaryAdvanced = salaryAdvanced;
        this.EPFPay = EPFPay;
        this.ETFPay = ETFPay;
        this.netSalary = netSalary;
    }

    public MonthlyFinalSalary(int monthlyWorkID, double totalAllowencesRate, double noPay, double otPay, double salaryAdvanced, double EPFPay, double ETFPay, double netSalary) {
        this.monthlyWorkID = monthlyWorkID;
        this.totalAllowencesRate = totalAllowencesRate;
        this.noPay = noPay;
        this.otPay = otPay;
        this.salaryAdvanced = salaryAdvanced;
        this.EPFPay = EPFPay;
        this.ETFPay = ETFPay;
        this.netSalary = netSalary;
    }

    public int getMonthlyFinalID() {
        return monthlyFinalID;
    }

    public void setMonthlyFinalID(int monthlyFinalID) {
        this.monthlyFinalID = monthlyFinalID;
    }

    public int getMonthlyWorkID() {
        return monthlyWorkID;
    }

    public void setMonthlyWorkID(int monthlyWorkID) {
        this.monthlyWorkID = monthlyWorkID;
    }

    public double getTotalAllowencesRate() {
        return totalAllowencesRate;
    }

    public void setTotalAllowencesRate(double totalAllowencesRate) {
        this.totalAllowencesRate = totalAllowencesRate;
    }

    public double getNoPay() {
        return noPay;
    }

    public void setNoPay(double noPay) {
        this.noPay = noPay;
    }

    public double getOtPay() {
        return otPay;
    }

    public void setOtPay(double otPay) {
        this.otPay = otPay;
    }

    public double getSalaryAdvanced() {
        return salaryAdvanced;
    }

    public void setSalaryAdvanced(double salaryAdvanced) {
        this.salaryAdvanced = salaryAdvanced;
    }

    public double getEPFPay() {
        return EPFPay;
    }

    public void setEPFPay(double EPFPay) {
        this.EPFPay = EPFPay;
    }

    public double getETFPay() {
        return ETFPay;
    }

    public void setETFPay(double ETFPay) {
        this.ETFPay = ETFPay;
    }

    public double getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(double netSalary) {
        this.netSalary = netSalary;
    }
}
