package lk.ijse.payroll.entity;

public class Employee {
    private int empId;
    private String empName;
    private String empNIC;
    private String empAddress;
    private DesignationDetails designationDetails;
    private String designation;

    public Employee() {
    }

    public Employee(int empId, String empName, String empNIC, String empAddress, String designation) {
        this.empId = empId;
        this.empName = empName;
        this.empNIC = empNIC;
        this.empAddress = empAddress;
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Employee(int empId, String empName, String empNIC, String empAddress, DesignationDetails designationDetails) {
        this.empId = empId;
        this.empName = empName;
        this.empNIC = empNIC;
        this.empAddress = empAddress;
        this.designationDetails = designationDetails;
    }

    public Employee(int empId, String empName, String empNIC, String empAddress) {
        this.empId = empId;
        this.empName = empName;
        this.empNIC = empNIC;
        this.empAddress = empAddress;
    }

    public Employee(String empName, String empNIC, String empAddress, DesignationDetails designationDetails) {
        this.empName = empName;
        this.empNIC = empNIC;
        this.empAddress = empAddress;
        this.designationDetails = designationDetails;
    }

    public Employee(String empName, String empNIC, String empAddress) {
        this.empName = empName;
        this.empNIC = empNIC;
        this.empAddress = empAddress;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpNIC() {
        return empNIC;
    }

    public void setEmpNIC(String empNIC) {
        this.empNIC = empNIC;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public DesignationDetails getDesignationDetails() {
        return designationDetails;
    }

    public void setDesignationDetails(DesignationDetails designationDetails) {
        this.designationDetails = designationDetails;
    }
}
