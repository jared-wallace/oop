package resources.CarInventoryManagement;
/**
 * @(#)Car.java
 *
 *
 * @author Mason Egger
 * @author Jared Wallace
 * @version %I%, %G%
 */

import java.io.Serializable;

class Car extends Vehicle implements Serializable {

    private String bodyStyle = "";

    /**
     * This is the regular constructor, that is, the one that is
     * expected to be invoked. This should only be called after each
     * field has passed validation.
     *
     * @param vin   The vin number of the new car
     * @param make  The make of the new car
     * @param model The model of the new car
     * @param year  The year of the new car
     * @param price The price of the new car
     */
    public Car(String vin, String make, String model, int year, double price, int mileage, String bodyStyle) {
        super(vin, make, model, price, mileage, year);
        this.setBodyStyle(bodyStyle);

    }

    /**
     * Currently, this only checks to make sure the body style is not an empty
     * string.
     *
     * @param bodyStyle the proposed body style of the car
     * @return True if the body style parameter was not an empty string
     */
    public static boolean validateBodyStyle(String bodyStyle) {
        return !bodyStyle.equals("");
    }

    /**
     * This method just adds the body style to the default vehicle string.
     *
     * @return A nicely formatted string of all Car fields.
     */
    @Override
    public String toString() {
        String orig = super.toString();
        return orig + " " + this.getBodyStyle();
    }


    String getBodyStyle() {
        return bodyStyle;
    }

    void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }
}