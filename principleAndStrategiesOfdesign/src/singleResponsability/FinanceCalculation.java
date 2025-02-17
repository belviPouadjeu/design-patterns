package singleResponsability;

public class FinanceCalculation {
    public double calcIncomeTaxForCurrentYear(Employee employee) {
        // Simple tax calculation: Assume 20% tax on a fixed salary of $50,000
        double salary = 50000;
        double taxRate = 0.2;
        return salary * taxRate;
    }
}
