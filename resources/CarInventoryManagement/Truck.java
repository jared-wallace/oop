package resources.CarInventoryManagement;

/**
 * Created by jared on 9/19/14.
 */
public class Truck extends Vehicle {

    private int maxLoadWeight = 0;
    private double lengthFT = 0;

    public Truck(int maxLoadWeight, float lengthFT, String vin, String make, String model, float price, int mileage, int year) {
        super(vin,make, model, price, mileage, year);
        this.setMaxLoadWeight(maxLoadWeight);
        this.setLengthFT(lengthFT);
    }

    public int getMaxLoadWeight() {
        return maxLoadWeight;
    }

    public void setMaxLoadWeight(int maxLoadWeight) {
        this.maxLoadWeight = maxLoadWeight;
    }

    public double getLengthFT() {
        return lengthFT;
    }

    public void setLengthFT(float lengthFT) {
        this.lengthFT = lengthFT;
    }
}
