package resources.CarInventoryManagement;
/**
 * @(#)Vehicle.java
 *
 *
 * @author Mason Egger
 * @author Jared Wallace
 * @version %I%, %G%
 */
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

abstract class Vehicle implements Serializable {
    private String vin = "";
    private String make = "";
    private String model = "";
    private double price = 0.0;
    private int year = 0;
    private int mileage;

    Vehicle(String vin, String make, String model, double price, int mileage, int year) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.price = price;
        this.mileage = mileage;
        this.year = year;
    }

    /**
     * Currently, the only validation is a length between 1 and 17 inclusive.
     * Although vin numbers are standard now, this was not always the case.
     *
     * @param vin A string representing the vin number of the vehicle
     * @return True if the string is 17 characters or less and not empty.
     */
    public static boolean validateVin(String vin) {
        return !(vin.equals("") && vin.length() > 17);
    }

    /**
     * The only validation for the "make" field is that the make
     * is a non empty string.
     *
     * @param make The make to be validated
     * @return True if the make is a non empty string, false otherwise.
     */
    public static boolean validateMake(String make) {
        return !make.equals("");
    }

    /**
     * The only validation for the "model" field is that the model
     * is indeed a string.
     *
     * @param model The model to be validated
     * @return True if the model is a non empty string, false otherwise.
     */
    public static boolean validateModel(String model) {
        return !model.equals("");
    }

    /**
     * The validation function for the year checks to make sure the year
     * is at least 1886 or later (The car was invented in 1886). The
     * function also checks for exactly 4 digits.
     *
     * @param year The year to be validated
     * @return True if the year is an integer greater than 1886 and exactly
     * 4 digits, otherwise it returns false.
     */
    public static boolean validateYear(int year) {
        return !((year < 1886) || (String.valueOf(year).length() != 4));
    }

    /**
     * This validation function only checks for a price that is
     * non-negative.
     *
     * @param price The price to be validated
     * @return True if price is non-negative, false otherwise
     */
    public static boolean validatePrice(double price) {
        return price >= 0.0;

    }

    /**
     * This overrides toString to give a nicely formatted Vehicle
     * output. The price is automatically formatted to exactly
     * two decimal places.
     *
     * @return A string consisting of all of the Vehicle objects
     * data fields.
     */
    public String toString() {
        NumberFormat df = new DecimalFormat("#0.00");
        String price_formatted = df.format(this.getPrice());
        return this.getVin() + " " + this.getMake() + " " + this.getModel() + " " + this.getYear() + " " + price_formatted;
    }

    public String getVin() {
        return vin;
    }

    String getMake() {
        return make;
    }

    String getModel() {
        return model;
    }

    /**
     * Getter function to retrieve the price of the Car object.
     *
     * @return The price of the Car object
     */
    public double getPrice() {
        return price;
    }

// --Commented out by Inspection START (9/22/14 11:33 AM):
//    public int getMileage() {
//        return mileage;
//    }
// --Commented out by Inspection STOP (9/22/14 11:33 AM)

    int getYear() {
        return year;
    }

}
