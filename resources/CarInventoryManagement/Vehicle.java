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
import java.util.Calendar;

abstract class Vehicle implements Serializable {
    private String vin = "";
    private String make = "";
    private String model = "";
    private double price = 0.0;
    private int year = 0;
    private int mileage;

    /**
     * The constructor for the vehicle class
     *
     * @param vin The VIN of the new vehicle
     * @param make The manufacturer of the new vehicle
     * @param model The model of the new vehicle
     * @param price The price of the new vehicle
     * @param mileage The mileage of the new vehicle
     * @param year The year of the new vehicle
     */
    Vehicle(String vin, String make, String model, double price, int mileage, int year) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.price = price;
        this.mileage = mileage;
        this.year = year;
    }

    /**
     * This method validates the VIN, by checking that the length of the VIN
     * is between 1 and 17 inclusive. Although VIN numbers are standard now,
     * this was not always the case.
     *
     * @param vin A string representing the VIN number of the vehicle
     * @return True if the vin parameter is 17 characters or less and not empty.
     */
    public static boolean validateVin(String vin) {
        return !(vin.equals("") && vin.length() > 17);
    }

    /**
     * This method validates the manufacturer, by ensuring that the
     * make parameter is a non-empty string.
     *
     * @param make The manufacturer to be validated
     * @return True if the manufacturer is a non empty string, false otherwise.
     */
    public static boolean validateMake(String make) {
        return !make.equals("");
    }

    /**
     * This method validates the model by ensuring that it's not an empty string.
     *
     * @param model The model to be validated
     * @return True if the model parameter is a non empty string, false otherwise.
     */
    public static boolean validateModel(String model) {
        return !model.equals("");
    }

    /**
     * The validation function for the year checks to make sure the year
     * is at least 1886 (The car was invented in 1886) and at most one year in
     * the future (since car dealerships sell next year's models this year). The
     * function also checks for exactly 4 digits.
     *
     * @param year The year to be validated
     * @return True if the year is an integer greater than 1886 and less than the current
     * year plus one, and exactly 4 digits, otherwise it returns false.
     */
    public static boolean validateYear(int year) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return !((year < 1886) || (String.valueOf(year).length() != 4) || (year > currentYear + 1));
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

    /**
     * Allows retrieving the VIN number of a vehicle.
     *
     * @return The VIN number of the vehicle object
     */
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
     * Allows retrieving the price of the car object.
     *
     * @return The price of the car object
     */
    public double getPrice() {
        return price;
    }

    int getYear() {
        return year;
    }

}
