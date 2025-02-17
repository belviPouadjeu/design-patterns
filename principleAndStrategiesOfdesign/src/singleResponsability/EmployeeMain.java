package singleResponsability;

import java.util.Date;

public class EmployeeMain {
    public static void main(String[] args) {
        Employee emp = new Employee("E123", "John Doe", "123 Street, City", new Date(120, 1, 1)); // Date(120,1,1) is 2020

        Promotion promotion = new Promotion();
        FinanceCalculation financeCalculation = new FinanceCalculation();

        System.out.println("Is promotion due? " + promotion.isPromotionDueThisYear(emp));
        System.out.println("Income Tax: xaf" + financeCalculation.calcIncomeTaxForCurrentYear(emp));
    }
}
