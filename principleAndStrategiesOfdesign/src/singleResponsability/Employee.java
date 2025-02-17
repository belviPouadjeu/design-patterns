package singleResponsability;

import java.util.Date;

public class Employee {
    private String employeeId;
    private String name;
    private String address;
    private Date dateOfJoining;

    // Constructor
    public Employee(String employeeId, String name, String address, Date dateOfJoining) {
        this.employeeId = employeeId;
        this.name = name;
        this.address = address;
        this.dateOfJoining = dateOfJoining;
    }

    // Getters
    public String getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public Date getDateOfJoining() { return dateOfJoining; }
}
