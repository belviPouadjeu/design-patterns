# Single Responsibility Principle (SRP) in Design Patterns

## Introduction
The **Single Responsibility Principle (SRP)** is one of the SOLID principles of object-oriented design. It states that:
> "A class should have only one reason to change."

This means that a class should focus on a **single responsibility** and should not be burdened with multiple concerns. Following SRP makes code **more maintainable, readable, and scalable**.

## Code Example
This example demonstrates **SRP** by separating different responsibilities into different classes.

### **1. Employee Class** (Data Holder)
```java
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
```
### **2. Promotion Class** (Handles Employee Promotion Logic)
```java
package singleResponsability;

public class Promotion {
    public boolean isPromotionDueThisYear(Employee employee) {
        // Sample promotion logic: Employees get a promotion after 3 years
        long yearsWorked = (System.currentTimeMillis() - employee.getDateOfJoining().getTime()) / (1000L * 60 * 60 * 24 * 365);
        return yearsWorked >= 3;
    }
}
```
### **3. FinanceCalculation Class** (Handles Employee Tax Calculation)
```java
package singleResponsability;

public class FinanceCalculation {
    public double calcIncomeTaxForCurrentYear(Employee employee) {
        // Simple tax calculation: Assume 20% tax on a fixed salary of xaf500,000
        double salary = 50000;
        double taxRate = 0.2;
        return salary * taxRate;
    }
}
```
### **4. Main Class** (To Test Our Code)
```java
package singleResponsability;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Employee emp = new Employee("E123", "John Doe", "123 Street, City", new Date(120, 1, 1)); // Date(120,1,1) is 2020
        
        Promotion promotion = new Promotion();
        FinanceCalculation financeCalculation = new FinanceCalculation();

        System.out.println("Is promotion due? " + promotion.isPromotionDueThisYear(emp));
        System.out.println("Income Tax: xaf" + financeCalculation.calcIncomeTaxForCurrentYear(emp));
    }
}
```

## **Why This Code Follows SRP**
✅ **Separation of Concerns**: Each class handles only one responsibility.

✅ **Modular Design**: If we need to change the promotion logic or tax calculation, we can do so without affecting the Employee class.

✅ **Improved Maintainability**: Any updates to the logic can be done independently without side effects on unrelated parts of the system.

## **Conclusion**
The **Single Responsibility Principle** ensures better code organization by ensuring that each class has a single focus. This leads to **cleaner, maintainable, and scalable software**. By applying SRP, we reduce dependencies and make our system more adaptable to future changes.

---


