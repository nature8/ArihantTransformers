
package spr.dto;

import java.util.Date;

public class Quotation {
    private String empId;
    private String empName;
    private String date;
    private int noOfClient;
    private double salaryPerDay;
    private double incentive;
    private double totSal;

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNoOfClient() {
        return noOfClient;
    }

    public void setNoOfClient(int noOfClient) {
        this.noOfClient = noOfClient;
    }

    public double getSalaryPerDay() {
        return salaryPerDay;
    }

    public void setSalaryPerDay(double salaryPerDay) {
        this.salaryPerDay = salaryPerDay;
    }

    public double getIncentive() {
        return incentive;
    }

    public void setIncentive(double incentive) {
        this.incentive = incentive;
    }

    public double getTotSal() {
        return totSal;
    }

    public void setTotSal(double totSal) {
        this.totSal = totSal;
    }

    public Quotation() {
    }

    public Quotation(String empId, String empName, String date, int noOfClient, double salaryPerDay, double incentive, double totSal) {
        this.empId = empId;
        this.empName = empName;
        this.date = date;
        this.noOfClient = noOfClient;
        this.salaryPerDay = salaryPerDay;
        this.incentive = incentive;
        this.totSal = totSal;
    }
    
}
