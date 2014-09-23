package resources.CarInventoryManagement;
/**
 * @(#)Truck.java
 *
 *
 * @author Mason Egger
 * @author Jared Wallace
 * @version %I%, %G%
 */

import java.io.Serializable;

public class Truck extends Vehicle implements Serializable {

    private int maxLoadWeight = 0;
    private double lengthFT = 0;

    /**
     * The constructor for the Truck class.
     *
     * @param vin The VIN number of the new truck
     * @param make The manufacturer of the new truck
     * @param model The model of the new truck
     * @param year The year of the new truck
     * @param price The price of the new truck
     * @param mileage The mileage of the new truck
     * @param maxLoadWeight The maximum load weight, in pounds, of the new truck
     * @param lengthFT The length of the new truck, in feet
     */
    public Truck(String vin, String make, String model, int year, double price, int mileage, int maxLoadWeight,
                 double lengthFT) {
        super(vin, make, model, price, mileage, year);
        this.setMaxLoadWeight(maxLoadWeight);
        this.setLengthFT(lengthFT);
    }

    /**
     * This method validates the maximum load weight, checking that the number is not
     * negative.
     *
     * @param maxLoadWeight the proposed maximum load weight the truck can accept
     * @return True if the maxLoadWeight parameter is greater than zero
     */
    public static boolean validateMaxLoadWeight(int maxLoadWeight) {
        return !(maxLoadWeight <= 0);
    }

    /**
     * This method validates the length, checking that the number is not negative.
     *
     * @param lengthFT the proposed length in feet of the truck
     * @return True if the lengthFT parameter is greater than zero
     */
    public static boolean validateLengthFT(double lengthFT) {
        return !(lengthFT <= 0);
    }

    /**
     * This method overrides toString to return a nicely formatted string
     * consisting of all the truck objects fields.
     *
     * @return A nicely formatted string with all the truck fields.
     */
    @Override
    public String toString() {
        String orig = super.toString();
        return orig + this.getMaxLoadWeight() + "lbs load cap., " + this.getLengthFT();
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
