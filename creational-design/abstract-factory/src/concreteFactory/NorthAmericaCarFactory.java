package concreteFactory;

import concreteProducts.Car;
import concreteProducts.CarSpecification;
import concreteProducts.NorthAmericaSpecification;
import concreteProducts.Sedan;

public class NorthAmericaCarFactory implements CarFactory {
    @Override
    public Car createCar() {
        return new Sedan();
    }

    @Override
    public CarSpecification createCarSpecification() {
        return new NorthAmericaSpecification();
    }
}
