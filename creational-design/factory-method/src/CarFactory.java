public class CarFactory extends VehicleFactory{ // Concrete Factory for Car

    @Override
    public Vehicle createVehicle() {
        return new Car();
    }
}
