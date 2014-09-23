package resources.CarInventoryManagement;
/**
 * @(#)Employee.java
 *
 *
 * @author Mason Egger
 * @author Jared Wallace
 * @version %I%, %G%
 */

import java.io.Serializable;


class Employee extends Person implements Serializable {
    private double salary;
    private int accountNumber;

    /**
     * This is the constructor for the Employee class.
     *
     * @param id The UID for the new employee
     * @param firstName The first name for the new employee
     * @param lastName The last name for the new employee
     * @param salary The monthly salary for the new employee
     * @param accountNumber The direct deposit account number for the new employee
     */
    public Employee(int id, String firstName, String lastName, double salary, int accountNumber) {
        super(id, firstName, lastName);
        this.salary = salary;
        this.accountNumber = accountNumber;
    }

    /**
     * This method validates the salary figure, merely ensuring
     * the salary is not negative.
     *
     * @param salary The salary figure to validate
     * @return True if the salary figure was not negative, false otherwise.
     */
    public static boolean validateSalary(double salary) {
        return !(salary < 0.0);
    }

    /**
     * This method validates the account number. The type of accounts we are
     * interested in validating (ACH capable) are limited to numeric characters only,
     * between 4 and 17 characters.
     *
     * @param accountNumber The employee account number to validate.
     * @return True if the account number is an integer between 4 and 17 digits
     */
    public static boolean validateAccountNumber(int accountNumber) {
        return !(String.valueOf(accountNumber).length() < 4 && String.valueOf(accountNumber).length() > 17);
    }

    int getAccountNumber() {
        return accountNumber;
    }

    /**
     * Allows setting the employee's account number.
     *
     * @param accountNumber The new account number
     */
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    double getSalary() {
        return salary;
    }

    /**
     * Allows setting the employee's salary.
     *
     * @param salary The new salary for the employee
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * This method overrides toString to return a nicely formatted
     * string consisting of all the employee object's fields
     *
     * @return A nicely formatted string
     */
    @Override
    public String toString() {
        return super.toString() + " " + "Employee " + this.getSalary() + " " + this.getAccountNumber();
    }

}