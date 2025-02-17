package openClosePrincipal;

public class Rectangle implements Shape {
    public double width;
    public double length;

    @Override
    public double calculateArea() {
        return length * width;
    }
}
