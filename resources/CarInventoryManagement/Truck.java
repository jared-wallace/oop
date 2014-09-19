package resources.CarInventoryManagement;

/**
 * Created by jared on 9/19/14.
 */
public class Truck extends Vehicle {

    public Truck(int maxLoadWeight, float lengthFT, String vin, String make, String model, float price, int mileage, int year) {
        super(vin,make, model, price, mileage, year);
        int maxLoadWeight1 = maxLoadWeight;
        float lengthFT1 = lengthFT;
    }
}
