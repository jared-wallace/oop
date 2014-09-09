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

public class Car
{
	private String lpNumber, make, model;
	private int year;
	private double price;

    /**
     * This is the regular constructor, that is, the one that is
     * expected to be invoked. This should only be called after each
     * field has passed validation.
     *
     * @param lpNumber The license plate number of the new car
     * @param make The make of the new car
     * @param model The model of the new car
     * @param year The year of the new car
     * @param price The price of the new car
     */
    public Car(String lpNumber, String make, String model, int year, double price) 
    {
    	this.lpNumber = lpNumber;
    	this.make = make;
    	this.model = model;
    	this.year = year;
    	this.price = price;
    }

    /**
     * This constructor should never be called. It's only
     * here as a safety measure.
     */
    public Car()
    {
    	this("XXX-XXX","Empty","Empty",0,0.0);
    }

    /**
     * The validation function allows any combination of numbers and
     * letters, as well as periods, spaces and dashes. This is in
     * accordance with TxDOT's current rule set for license plates.
     * Unfortunately, TxDOT also allows texas symbols and heart symbols,
     * neither of which can be entered. Instead, the texas symbol should
     * be entered as "*" and the heart as "@".
     *
     * @param lp The license plate number to be checked for validity.
     * @return True for a valid plate, false otherwise.
     */
    public static boolean validateLP(String lp)
    {
        if(lp.matches("[\\w\\./[ ]\\*@]{1,7}"))
            return true;
        return false;
    }

    /**
     * The only validation for the "make" field is that the make
     * is a non empty string.
     *
     * @param make
     * @return True if the make is a non empty string, false otherwise.
     */
    public static boolean validateMake(String make)
    {
        if(make.equals(""))
            return false;
        return true;
    }

    /**
     * The only validation for the "model" field is that the model
     * is indeed a string.
     *
     * @param model
     * @return True if the model is a non empty string, false otherwise.
     */
    public static boolean validateModel(String model)
    {
        if(model.equals(""))
            return false;
        return true;
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
    public static boolean validateYear(int year)
    {
        if((year < 1886) || (String.valueOf(year).length() != 4))
            return false;
        return true;
    }

    /**
     * This validation function only checks for a price that is
     * non-negative.
     *
     * @param price
     * @return True if price is non-negative, false otherwise
     */
    public static boolean validatePrice(double price)
    {
        if(price < 0.0)
            return false;
        return true;
        
    }

    /**
     * Getter function to retrieve the price of the Car object.
     *
     * @return The price of the Car object
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * Getter function to retrieve the license plate of the Car object.
     * @return The license plate of the Car object
     */
    public String getLpNumber()
    {
        return lpNumber;
    }

    /**
     * This overrides toString to give a nicely formatted Car
     * output. The price is automatically formatted to exactly
     * two decimal places.
     *
     * @return A string consisting of all of the Car objects
     * data fields.
     */
    public String toString()
    {
        NumberFormat df = new DecimalFormat("#0.00");
        String price_formatted = df.format(price);
        return lpNumber + " " + make + " " + model + " " + year + " " + price_formatted;
    }
    
    
}