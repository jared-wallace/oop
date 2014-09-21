package resources.CarInventoryManagement;

/**
 * Created by jared on 9/19/14.
 */
 
import java.io.*;

public class Truck extends Vehicle implements Serializable
{

    private int maxLoadWeight = 0;
    private double lengthFT = 0;

    public Truck(String vin, String make, String model, int year, double price, int mileage, int maxLoadWeight,
                 double lengthFT) {
        super(vin, make, model, price, mileage, year);
        this.setMaxLoadWeight(maxLoadWeight);
        this.setLengthFT(lengthFT);
    }

    /**
     * Currently only checking for non zero numbers
     *
     * @param maxLoadWeight the proposed maximum load weight the truck can accept
     * @return True if the maxLoadWeight parameter is greater than zero
     */
    public static boolean validateMaxLoadWeight(int maxLoadWeight) {
        return !(maxLoadWeight >= 0);
    }

    /**
     * Currently only checking for non zero numbers
     *
     * @param lengthFT the proposed length in feet of the truck
     * @return True if the lengthFT parameter is greater than zero
     */
    public static boolean validateLengthFT(double lengthFT) {
        return !(lengthFT >= 0);
    }

    /**
     * Adds max load weight and length to the default Vehicle string.
     *
     * @return A nicely formatted string with all the Truck fields.
     */
    @Override
    public String toString() {
        String orig = super.toString();
        return orig + this.getMaxLoadWeight() + this.getLengthFT();
    }

    int getMaxLoadWeight() {
        return maxLoadWeight;
    }

    void setMaxLoadWeight(int maxLoadWeight) {
        this.maxLoadWeight = maxLoadWeight;
    }

    double getLengthFT() {
        return lengthFT;
    }

    void setLengthFT(double lengthFT) {
        this.lengthFT = lengthFT;
    }
}
