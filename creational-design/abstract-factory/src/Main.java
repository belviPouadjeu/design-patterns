import concreteFactory.CarFactory;
import concreteFactory.EuropeCarFactory;
import concreteFactory.NorthAmericaCarFactory;
import concreteProducts.Car;
import concreteProducts.CarSpecification;

public class Main {
    public static void main(String[] args) {
        // Creating cars for North America
        CarFactory northAmericaFactory = new NorthAmericaCarFactory();
        Car northAmericaCar = northAmericaFactory.createCar();
        CarSpecification northAmericaSpec = northAmericaFactory.createCarSpecification();

        northAmericaCar.assemble();
        northAmericaSpec.display();

        // Creating cars for Europe
        CarFactory europeFactory = new EuropeCarFactory();
        Car europeCar = europeFactory.createCar();
        CarSpecification europeSpec = europeFactory.createCarSpecification();
        europeCar.assemble();
        europeSpec.display();
    }
}