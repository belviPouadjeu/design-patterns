public class BikeFactory extends VehicleFactory { // Concrete Factory for Bike
    @Override
    public Vehicle createVehicle() {
        return new Bike();
    }
}
