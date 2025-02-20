package concreteFactory;

import concreteProducts.Car;
import concreteProducts.CarSpecification;

public interface CarFactory {
    Car createCar();
    CarSpecification createCarSpecification();
}
