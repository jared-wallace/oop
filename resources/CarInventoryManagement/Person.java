package resources.CarInventoryManagement;
/**
 * @(#)Person.java
 *
 *
 * @author Mason Egger
 * @author Jared Wallace
 * @version %I%, %G%
 */

import java.io.Serializable;


abstract class Person implements Serializable {

    private final int id;
    private String firstName, lastName;

    Person(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * This method validates the name as being at least one character long.
     *
     * @param name The name to validate
     * @return True if the name was at least one character long, false otherwise
     */
    public static boolean validateName(String name) {
        return !(name.length() < 1);
    }

    int getID() {
        return id;
    }

    String getFirstName() {
        return firstName;
    }

    /**
     * This method allows setting the first name of a person
     *
     * @param firstName The new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    String getLastName() {
        return lastName;
    }

    /**
     * This method allows setting the last name of a person.
     * @param lastName The new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * This method overrides toString to return a nicely formatted string consisting
     * of the UID, last name and first name of a person. This is never called by itself,
     * but rather by Person's child classes.
     *
     * @return A nicely formatted string
     */
    @Override
    public String toString() {
        return this.id + " " + this.getLastName() + "," + this.getFirstName();
    }

}
