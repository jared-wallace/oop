package resources.CarInventoryManagement;
/**
 * @(#)Motorcycle.java
 *
 *
 * @author Mason Egger
 * @author Jared Wallace
 * @version %I%, %G%
 */

import java.io.Serializable;

public class Motorcycle extends Vehicle implements Serializable {
    private String type;
    private int displacement;

    /**
     * The constructor for the Motorcycle class.
     *
     * @param vin The VIN number of the new motorcycle
     * @param make The manufacturer of the new motorcycle
     * @param model The model of the new motorcycle
     * @param year The year of the new motorcycle
     * @param price The price of the new motorcycle
     * @param mileage The mileage of the new motorcycle
     * @param type The type of the new motorcycle (cruiser, dual-purpose, etc)
     * @param displacement The displacement in cubic centimeters of the new motorcycle
     */
    public Motorcycle(String vin, String make, String model, int year, double price, int mileage, String type, int displacement) {
        super(vin, make, model, price, mileage, year);
        this.setType(type);
        this.setDisplacement(displacement);
    }


    /**
     * Checks to see if the type is blank since there is no set list of types.
     *
     * @param type - The type of motorcycle (Scooter, Touring, Cruiser, etc.)
     * @return True if the type is not blank, false otherwise
     */
    public static boolean validateType(String type) {
        return !(type.equals(""));
    }

    /**
     * This method checks whether the displacement is less than 50cc.
     *
     * @param displacement Integer value representing displacement in cubic centimeters
     * @return True if the displacement is not less than 50
     */
    public static boolean validateDisplacement(int displacement) {
        return !(displacement < 50);
    }

    /**
     * This method overrides toString to return a nicely formatted string
     * consisting of all the motorcycle object's fields.
     *
     * @return A nicely formatted string.
     */
    @Override
    public String toString() {
        String orig = super.toString();
        return orig + " " + this.getDisplacement() + "cc, " + this.getType();
    }

    int getDisplacement() {
        return displacement;
    }

    void setDisplacement(int displacement) {
        this.displacement = displacement;
    }

    String getType() {
        return type;
    }

    void setType(String type) {
        this.type = type;
    }
}