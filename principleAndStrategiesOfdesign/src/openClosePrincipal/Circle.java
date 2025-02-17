package openClosePrincipal;

public class Circle implements Shape {
    public double radius;

    @Override
    public double calculateArea() {
        return ((double) 22 /7) * radius * radius;
    }
}
