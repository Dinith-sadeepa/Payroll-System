package lk.ijse.payroll.entity;

public class ConfigTable {
    private String configDescription;
    private int configRate;

    public ConfigTable(String configDescription, int configRate) {
        this.configDescription = configDescription;
        this.configRate = configRate;
    }

    public ConfigTable() {
    }

    public String getConfigDescription() {
        return configDescription;
    }

    public void setConfigDescription(String configDescription) {
        this.configDescription = configDescription;
    }

    public int getConfigRate() {
        return configRate;
    }

    public void setConfigRate(int configRate) {
        this.configRate = configRate;
    }
}

