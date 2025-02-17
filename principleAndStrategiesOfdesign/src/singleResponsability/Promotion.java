package singleResponsability;

public class Promotion {
    public boolean isPromotionDueThisYear(Employee employee) {
        // Sample promotion logic: Employees get a promotion after 3 years
        long yearsWorked = (System.currentTimeMillis() - employee.getDateOfJoining().getTime()) / (1000L * 60 * 60 * 24 * 365);
        return yearsWorked >= 3;
    }
}
