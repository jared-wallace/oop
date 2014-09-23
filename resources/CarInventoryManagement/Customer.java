package resources.CarInventoryManagement;
/**
 * @(#)Customer.java
 *
 *
 * @author Mason Egger
 * @author Jared Wallace
 * @version %I%, %G%
 */

import java.io.Serializable;

public class Customer extends Person implements Serializable {
    private int dlNumber;
    private String phoneNumber;

    /**
     * Constructor for the Customer class.
     *
     * @param id The UID for the new customer
     * @param firstName The first name of the new customer
     * @param lastName The last name of the new customer
     * @param phoneNumber The phone number of the new customer
     * @param dlNumber The driver's license number of the new customer
     */
    public Customer(int id, String firstName, String lastName, String phoneNumber, int dlNumber) {
        super(id, firstName, lastName);
        this.setPhoneNumber(phoneNumber);
        this.setDriversLicense(dlNumber);
    }

    /**
     * This method validates the phone number. Formats like
     * xxxxxxxxxx, xxx-xxx-xxxx, xxx.xxx.xxxx, xxx xxx xxxx and
     * even numbers with an extension should all validate correctly.
     *
     * @param phoneNumber The phone number to validate.
     * @return True if the number validated successfully, false otherwise.
     */
    public static boolean validatePhoneNumber(String phoneNumber) {
        //validate phone numbers of format "1234567890"
        if (phoneNumber.matches("\\d{10}")) {
            return true;
        }
        //validating phone number with -, . or spaces
        else
            return phoneNumber.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")
                    || phoneNumber.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")
                    || phoneNumber.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}");
    }

    /**
     * This method validates the driver's license number. It validates
     * according to Texas Department of Transportation guidelines only.
     *
     * @param dlNumber The driver's license number to validate.
     * @return True if the license number validated successfully, false otherwise.
     */
    public static boolean validateDriversLicense(int dlNumber) {
        //every state has a different setup for license numbers
        //since the requirements specify int we are going to go with
        //Texas Drivers license which is 7-8 ints long

        return !(dlNumber < 1000000 || dlNumber > 99999999);
    }

    String getPhoneNumber() {
        return phoneNumber;
    }

    void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    int getDriversLicense() {
        return dlNumber;
    }

    void setDriversLicense(int dlNumber) {
        this.dlNumber = dlNumber;
    }

    /**
     * This method overrides toString to return a nice, formatted representation of
     * the customer object.
     *
     * @return A nicely formatted string consisting of the customer object fields.
     */
    @Override
    public String toString() {
        return super.toString() + "Customer " + this.getPhoneNumber() + " " + this.getDriversLicense();
    }
}