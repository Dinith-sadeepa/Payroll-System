package lk.ijse.payroll.entity;

public class Designation {
    private int desId;
    private String desDescription;
    private double desBasicSalary;

    public Designation() {
    }

    public Designation(int desId, String desDescription, double desBasicSalary) {
        this.desId = desId;
        this.desDescription = desDescription;
        this.desBasicSalary = desBasicSalary;
    }

    public int getDesId() {
        return desId;
    }

    public void setDesId(int desId) {
        this.desId = desId;
    }

    public String getDesDescription() {
        return desDescription;
    }

    public void setDesDescription(String desDescription) {
        this.desDescription = desDescription;
    }

    public double getDesBasicSalary() {
        return desBasicSalary;
    }

    public void setDesBasicSalary(double desBasicSalary) {
        this.desBasicSalary = desBasicSalary;
    }
}
