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

    private String bodyStyle = "";

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
        this.setBodyStyle(bodyStyle);

    }

    /**
     * Currently, this only checks to make sure the bodystyle is not an empty
     * string. In the future, we should check against an accepted list of styles.
     *
     * @param bodyStyle the proposed body style of the car
     * @return True if the bodystyle parameter was not an empty string
     */
    public static boolean validateBodyStyle(String bodyStyle) {
        return !bodyStyle.equals("");
    }

    /**
     * Just adds the body style to the default vehicle string.
     *
     * @return A nicely formatted string of all Car fields.
     */
    @Override
    public String toString() {
        String orig = super.toString();
        return orig + this.getBodyStyle();
    }


    public String getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }
}