package resources.CarInventoryManagement;
/**
 * @(#)Car.java
 *
 *
 * @author Mason Egger and Jared Wallace
 * @version %I%, %G%
 */

import java.text.DecimalFormat;
import java.text.NumberFormat;

class Car extends Vehicle {

    /**
     * This is the regular constructor, that is, the one that is
     * expected to be invoked. This should only be called after each
     * field has passed validation.
     *
     * @param vin The vin number of the new car
     * @param make     The make of the new car
     * @param model    The model of the new car
     * @param year     The year of the new car
     * @param price    The price of the new car
     */
    public Car(String vin, String make, String model, int year, double price, int mileage, String bodyStyle) {
        super(vin, make, model, price, mileage, year);
        String bodyStyle1 = bodyStyle;

    }

    /**
     * This overrides toString to give a nicely formatted Car
     * output. The price is automatically formatted to exactly
     * two decimal places.
     *
     * @return A string consisting of all of the Car objects
     * data fields.
     */
    public String toString() {
        NumberFormat df = new DecimalFormat("#0.00");
        String price_formatted = df.format(this.getPrice());
        return this.getVin() + " " + this.getMake() + " " + this.getModel() + " " + this.getModel() + " " + price_formatted;
    }


}