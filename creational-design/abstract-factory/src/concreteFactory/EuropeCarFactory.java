package concreteFactory;

import concreteProducts.Car;
import concreteProducts.CarSpecification;
import concreteProducts.EuropeSpecification;
import concreteProducts.Hatchback;

public class EuropeCarFactory implements CarFactory {
    @Override
    public Car createCar() {
        return new Hatchback();
    }

    @Override
    public CarSpecification createCarSpecification() {
        return new EuropeSpecification();
    }
}
