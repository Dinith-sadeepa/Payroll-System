package lk.ijse.payroll.entity;

public class Allowences {

    private String allowencesDescription;
    private int allowencesRate;

    public Allowences() {
    }

    public Allowences(String allowencesDescription, int allowencesRate) {
        this.allowencesDescription = allowencesDescription;
        this.allowencesRate = allowencesRate;
    }

    public String getAllowencesDescription() {
        return allowencesDescription;
    }

    public void setAllowencesDescription(String allowencesDescription) {
        this.allowencesDescription = allowencesDescription;
    }

    public int getAllowencesRate() {
        return allowencesRate;
    }

    public void setAllowencesRate(int allowencesRate) {
        this.allowencesRate = allowencesRate;
    }
}
