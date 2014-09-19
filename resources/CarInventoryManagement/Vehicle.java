package resources.CarInventoryManagement;

/**
 * Created by jared on 9/19/14.
 */
abstract class Vehicle {
    private String vin = "";
    private String make = "";
    private String model = "";
    private double price = 0.0;
    private int mileage = 0;
    private int year = 0;

    public Vehicle(String vin, String make, String model, double price, int mileage, int year) {
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
    public static boolean validateVin(String vin){
        return !(vin.equals("") && vin.length() < 17);
    }
    /**
     * The only validation for the "make" field is that the make
     * is a non empty string.
     *
     * @param make
     * @return True if the make is a non empty string, false otherwise.
     */
    public static boolean validateMake(String make) {
        return !make.equals("");
    }

    /**
     * The only validation for the "model" field is that the model
     * is indeed a string.
     *
     * @param model
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
     * @param year
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
     * @param price
     * @return True if price is non-negative, false otherwise
     */
    public static boolean validatePrice(double price) {
        return price >= 0.0;

    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Getter function to retrieve the price of the Car object.
     *
     * @return The price of the Car object
     */
    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
