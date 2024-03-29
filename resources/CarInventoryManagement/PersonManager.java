package resources.CarInventoryManagement;
/**
 * @(#)PersonManager.java
 *
 *
 * @author Mason Egger
 * @author Jared Wallace
 * @version %I%, %G%
 */

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.err;
import static java.lang.System.out;

class PersonManager {

    private static ArrayList<Person> peopleDB;

    /**
     * The constructor for PersonManager creates an ArrayList of Person objects
     * called <code>peopleDB</code>. This ArrayList will serve as the runtime
     * people database.
     */
    public PersonManager() {

        peopleDB = new ArrayList<Person>();
        if (!readDB()) {
            err.println("People database corrupted or doesn't exist");
        } else {
            out.println("Successfully read database people.db");
        }
    }

    /**
     * This method returns the runtime database peopleDB
     *
     * @return The runtime people database called peopleDB
     */
    public ArrayList<Person> getPersonDB() {
        return peopleDB;
    }


    boolean addUser(Scanner kb) {
        int uID;
        String lastName, firstName, type;

        out.println("Please enter the type of user you wish to add. (employee or customer)");
        type = kb.next();
        uID = getNewUID();

        out.println("Enter the first name of the user.");
        firstName = kb.next();
        firstName = Character.toUpperCase(firstName.charAt(0)) + firstName.substring(1).toLowerCase();
        out.println("Enter the last name of the user.");
        lastName = kb.next();
        lastName = Character.toUpperCase(lastName.charAt(0)) + lastName.substring(1).toLowerCase();

        if (type.matches("[cC]\\w*"))
            return addCustomer(kb, uID, lastName, firstName);
        else
            return addEmployee(kb, uID, lastName, firstName);
    }

    /**
     * Adds an employee to the runtime database, (assuming all the fields validate correctly).
     * This does not mean that the information will necessarily end up in the database
     * file! The database file is only written upon exiting the program normally!
     *
     * @param kb        Scanner object for input/output
     * @param uID       The UID of the employee
     * @param firstName The first name of the employee
     * @param lastName  The last name of the employee
     * @return True if the employee was successfully added, false otherwise.
     */
    private boolean addEmployee(Scanner kb, int uID, String lastName, String firstName) {
        double salary = -1.0;
        int accountNumber = -1;
        boolean valid = false;
        out.println("Please enter the monthly salary of the employee");
        while (!valid) {
            try {
                salary = kb.nextDouble();
                valid = true;
            } catch (InputMismatchException e1) {
                err.println("Error: That salary number was not valid.");
                err.println("Please try again.");
                kb.next();
            }
        }

        valid = false;
        out.println("Please enter the bank account number for direct deposit");
        while (!valid) {
            try {
                accountNumber = kb.nextInt();
                valid = true;
            } catch (InputMismatchException e1) {
                err.println("Error: That account number was invalid.");
                err.println("Please try again.");
                kb.next();
            }
        }

        return Person.validateName(firstName) && Person.validateName(lastName)
                && Employee.validateSalary(salary) && Employee.validateAccountNumber(accountNumber)
                && peopleDB.add(new Employee(uID, firstName, lastName, salary, accountNumber));
    }

    /**
     * Adds a customer to the runtime database, (assuming all the fields validate correctly).
     * This does not mean that the information will necessarily end up in the database
     * file! The database file is only written upon exiting the program normally!
     *
     * @param kb        Scanner object for input/output
     * @param uID       The UID of the customer
     * @param firstName The first name of the customer
     * @param lastName  The last name of the customer
     * @return True if the customer was successfully added, false otherwise.
     */
    private boolean addCustomer(Scanner kb, int uID, String lastName, String firstName) {
        String phoneNumber;
        int dLNumber = -1;
        boolean valid = false;
        out.println("Please enter the phone number of the customer.");
        phoneNumber = kb.next();

        out.println("Please enter the driver's license number of the customer.");
        while (!valid) {
            try {
                dLNumber = kb.nextInt();
                valid = true;
            } catch (InputMismatchException e1) {
                err.println("Error: That license number was invalid.");
                err.println("Please try again.");
                kb.next();
            }
        }
        return Person.validateName(firstName) && Person.validateName(lastName)
                && Customer.validatePhoneNumber(phoneNumber) && Customer.validateDriversLicense(dLNumber)
                && peopleDB.add(new Customer(uID, firstName, lastName, phoneNumber, dLNumber));
    }

    /**
     * This method allows updating existing users information. The
     * only field that cannot be updated is UID, since that must
     * remain both static and unique. The method only allows for changing
     * one field at a time.
     *
     * @param kb The scanner object for console input and output
     * @return True if the user had a field successfully updated, false otherwise.
     */
    boolean updateUser(Scanner kb) {
        int userID = -1;
        boolean valid = false;

        out.println("Enter the UID of the User");
        while (!valid) {
            try {
                userID = kb.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                err.println("Not valid input");
                err.println("Please input the id of the User again");
                kb.next();
            }
        }
        int index = search(userID);
        if (index == -1) {
            err.println("User does not exist");
            return false;
        }
        int choice = -1;
        String changeString;
        int changeInteger;
        double changeDouble;

        out.println("Enter the number of the field you want to change");
        out.println("1. First Name");
        out.println("2. Last Name");
        if (peopleDB.get(index) instanceof Customer) {
            out.println("3. Phone Number");
            out.println("4. Drivers License Number");

        } else {
            out.println("3. Salary");
            out.println("4. Bank Account Number");
        }
        out.println("5. Cancel");
        valid = false;
        while (!valid) {
            try {
                choice = kb.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                err.println("Input was not an integer");
                err.println("Please reenter the option you wish to change. ");
                kb.next();
            }
        }

        switch (choice) {
            case 1:
                out.println("Enter the new first name");
                changeString = kb.next();
                if (Person.validateName(changeString)) {
                    peopleDB.get(index).setFirstName(changeString);
                    return true;
                } else {
                    out.println("Invalid name.");
                    return false;
                }
            case 2:
                out.println("Enter the new last name");
                changeString = kb.next();
                if (Person.validateName(changeString)) {
                    peopleDB.get(index).setLastName(changeString);
                    return true;
                } else {
                    out.println("Invalid name.");
                    return false;
                }
            case 3:
                if (peopleDB.get(index) instanceof Customer) {
                    out.println("Enter the new phone number");
                    changeString = kb.next();
                    if (Customer.validatePhoneNumber(changeString)) {
                        ((Customer) peopleDB.get(index)).setPhoneNumber(changeString);
                        return true;
                    } else {
                        out.println("Invalid phone number.");
                        return false;
                    }
                } else {
                    out.println("Enter the new salary");
                    changeDouble = kb.nextDouble();
                    if (Employee.validateSalary(changeDouble)) {
                        ((Employee) peopleDB.get(index)).setSalary(changeDouble);
                        return true;
                    } else {
                        out.println("Invalid salary.");
                        return false;
                    }
                }
            case 4:
                if (peopleDB.get(index) instanceof Customer) {
                    out.println("Enter the new drivers license");
                    changeInteger = kb.nextInt();
                    if (Customer.validateDriversLicense(changeInteger)) {
                        ((Customer) peopleDB.get(index)).setDriversLicense(changeInteger);
                        return true;
                    } else {
                        out.println("Invalid drivers license");
                        return false;
                    }
                } else {
                    out.println("Enter the new bank account number");
                    changeInteger = kb.nextInt();
                    if (Employee.validateAccountNumber(changeInteger)) {
                        ((Employee) peopleDB.get(index)).setAccountNumber(changeInteger);
                        return true;
                    } else {
                        out.println("Invalid bank account number");
                        return false;
                    }
                }
            case 5:
                return false;
            default:
                out.println("Not a correct choice.");
                return false;
        }
    }

    /**
     * Given a UID and a type of person, attempts to validate whether or not the user
     * is listed in the database as the provided type of person (customer or employee)
     *
     * @param UID An int that is the UID of the person being sought.
     * @param type The type of person to verify (employee or customer)
     * @return True if the UID belongs to a customer. Otherwise returns false.
     */
    public boolean validateUID(int UID, String type) {
        if (type.equals("Customer"))
            return UID < (peopleDB.size() + 1) && UID > 0 && peopleDB.get(search(UID)) instanceof Customer;
        else
            return UID < (peopleDB.size() + 1) && UID > 0 && peopleDB.get(search(UID)) instanceof Employee;
    }

    /**
     * This method searches the runtime people database for a given
     * UID.
     *
     * @param s The UID to search the database for
     * @return The index of the matching person, if found, otherwise -1
     */
    int search(int s) {
        for (int x = 0; x < peopleDB.size(); x++) {
            int UID = peopleDB.get(x).getID();
            if (s == UID) {
                return x;
            }
        }
        return -1;
    }


    /**
     * This method generates a new UID for a person. The UID
     * is simply generated based on database size.
     *
     * @return The UID generated for the new user
     */
    private static int getNewUID() {
        if (peopleDB.size() == 0) {
            return 1;
        }
        return (peopleDB.size() + 1);
    }

    /**
     * Attempts to write the existing runtime people database to the people database file.
     * The file will be overwritten, so hopefully this doesn't introduce any corruption.
     *
     * @return True if the people database was successfully written, false if there
     * was an IOException.
     */
    public boolean saveDB() {
        try {
            FileOutputStream outFile = new FileOutputStream("people.db");
            ObjectOutputStream pWriter = new ObjectOutputStream(outFile);
            pWriter.writeObject(peopleDB);
            pWriter.close();
            return true;
        } catch (IOException e1) {
            return false;
        }
    }

    /**
     * Attempts to read in from the database file called <code>people.db</code>
     * and load each person's info into the runtime people database.
     *
     * @return True if the file existed and the information was successfully
     * read into the ArrayList <code>peopleDB</code>, false otherwise.
     */
    boolean readDB() {
        try {
            InputStream file = new FileInputStream("people.db");
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput sc = new ObjectInputStream(buffer);
            peopleDB = (ArrayList<Person>) sc.readObject();
            return true;
        } catch (IOException e1) {
            return false;
        } catch (ClassNotFoundException e2) {
            return false;
        }
    }

}