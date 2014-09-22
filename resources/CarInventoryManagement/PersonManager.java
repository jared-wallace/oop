package resources.CarInventoryManagement;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.err;
import static java.lang.System.out;

/**
 * Created by jared on 9/21/14.
 */
class PersonManager {

    private static ArrayList<Person> personDB;

    /**
     * Default constructor creates an ArrayList of Person objects called <code>personDB</code>.
     * This ArrayList will serve as the runtime database.
     */
    public PersonManager() {

        personDB = new ArrayList<Person>();
        if (!readDB()) {
            err.println("People database corrupted or doesn't exist");
        } else {
            out.println("Successfully read database people.db");
        }
    }

    public ArrayList<Person> getPersonDB() {
        return personDB;
    }


    boolean addUser(Scanner kb) {
        int uID;
        String lastName, firstName, type;

        out.println("Please enter the type of user you wish to add. (employee or customer)");
        type = kb.next();
        uID = getNewUID(personDB);

        out.println("Enter the first name of the user.");
        firstName = kb.next();
        out.println("Enter the last name of the user.");
        lastName = kb.next();

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
     * @param kb            Scanner object for input/output
     * @param uID           The UID of the employee
     * @param firstName     The first name of the employee
     * @param lastName      The last name of the employee
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

        return Person.validateID(uID) && Person.validateName(firstName) && Person.validateName(lastName)
                && Employee.validateSalary(salary) && Employee.validateAccountNumber(accountNumber)
                && personDB.add(new Employee(uID, firstName, lastName, salary, accountNumber));
    }

    /**
     * Adds a customer to the runtime database, (assuming all the fields validate correctly).
     * This does not mean that the information will necessarily end up in the database
     * file! The database file is only written upon exiting the program normally!
     *
     * @param kb          Scanner object for input/output
     * @param uID         The UID of the customer
     * @param firstName   The first name of the customer
     * @param lastName    The last name of the customer
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
        return Person.validateID(uID) && Person.validateName(firstName) && Person.validateName(lastName)
                && Customer.validatePhoneNumber(phoneNumber) && Customer.validateDriversLicense(dLNumber)
                && personDB.add(new Customer(uID, firstName, lastName, phoneNumber, dLNumber));
    }

    boolean updateUser(Scanner kb) {
        int userID = -1;
        boolean valid = false;

        out.println("Enter the id of the User");
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
        valid = false;

        if (userID < 0 || userID > personDB.size() - 1) {
            err.println("User does not exist");
            return false;
        } else {
            int choice = -1;
            String changeS;
            int changeI;
            double changeD;
            if (personDB.get(userID) instanceof Customer) {
                out.println("Enter the number of the field you want to change");
                out.println("\t1. First Name");
                out.println("\t2. Last Name");
                out.println("\t3. Phone Number");
                out.println("\t4. Drivers License Number");
                out.println("\t5. Cancel");

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
                        out.println("Enter your first name");
                        changeS = kb.next();
                        if (Person.validateName(changeS)) {
                            personDB.get(userID).setFirstName(changeS);
                            return true;
                        } else {
                            out.println("Invalid name.");
                            return false;
                        }
                    case 2:
                        out.println("Enter your last name");
                        changeS = kb.next();
                        if (Person.validateName(changeS)) {
                            personDB.get(userID).setLastName(changeS);
                            return true;
                        } else {
                            out.println("Invalid name.");
                            return false;
                        }
                    case 3:
                        out.println("Enter your phone number");
                        changeS = kb.next();
                        if (Customer.validatePhoneNumber(changeS)) {
                            ((Customer) personDB.get(userID)).setPhoneNumber(changeS);
                            return true;
                        } else {
                            out.println("Invalid phone number.");
                            return false;
                        }
                    case 4:
                        out.println("Enter your drivers license");
                        changeI = kb.nextInt();
                        if (Customer.validateDriversLicense(changeI)) {
                            ((Customer) personDB.get(userID)).setDriversLicense(changeI);
                            return true;
                        } else {
                            out.println("Invalid drivers license");
                            return false;
                        }
                    case 5:
                        return false;

                    default:
                        out.println("Not a correct choice.");
                        return false;
                }

            } else {
                out.println("Enter the number of the field you want to change");
                out.println("\t1. First Name");
                out.println("\t2. Last Name");
                out.println("\t3. Salary");
                out.println("\t4. Bank Account Number");
                out.println("\t5. Cancel");

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
                        out.println("Enter your first name");
                        changeS = kb.next();
                        if (Person.validateName(changeS)) {
                            personDB.get(userID).setFirstName(changeS);
                            return true;
                        } else {
                            out.println("Invalid name.");
                            return false;
                        }
                    case 2:
                        out.println("Enter your last name");
                        changeS = kb.next();
                        if (Person.validateName(changeS)) {
                            personDB.get(userID).setLastName(changeS);
                            return true;
                        } else {
                            out.println("Invalid name.");
                            return false;
                        }
                    case 3:
                        out.println("Enter your salary");
                        changeD = kb.nextDouble();
                        if (Employee.validateSalary(changeD)) {
                            ((Employee) personDB.get(userID)).setSalary(changeD);
                            return true;
                        } else {
                            out.println("Invalid salary.");
                            return false;
                        }
                    case 4:
                        out.println("Enter your bank account number");
                        changeI = kb.nextInt();
                        if (Employee.validateAccountNumber(changeI)) {
                            ((Employee) personDB.get(userID)).setAccountNumber(changeI);
                            return true;
                        } else {
                            out.println("Invalid bank account number");
                            return false;
                        }
                    case 5:
                        return false;

                    default:
                        out.println("Not a correct choice.");
                        return false;
                }
            }
        }
    }

    /**
     * Given a UID, attempts to validate whether or not the user
     * is listed in the database as a customer
     *
     * @param UID An int that is the UID of the person being sought.
     * @return True if the UID belongs to a customer. Otherwise returns false.
     */
    public boolean validateCustomerUID(int UID) {
        return UID < (personDB.size() + 1) && UID > 0 && personDB.get(search(UID)) instanceof Customer;
    }

    /**
     * Given a UID, attempts to validate whether or not the user
     * is listed in the database as a employee
     *
     * @param UID An int that is the UID of the person being sought.
     * @return True if the UID belongs to a customer. Otherwise returns false.
     */
    public boolean validateEmployeeUID(int UID) {
        return UID < personDB.size() && UID > 0 && personDB.get(search(UID)) instanceof Employee;
    }

    int search(int s) {
        for (int x = 0; x < personDB.size(); x++) {
            int UID = personDB.get(x).getID();
            if (s == UID) {
                return x;
            }
        }
        return -1;
    }


    private static int getNewUID(ArrayList<Person> personDB) {
        if (personDB.size() == 0) {
            return 1;
        }
        return (personDB.get(personDB.size() - 1)).getID() + 1;
    }

    /**
     * Attempts to write the existing runtime database to the database file.
     * The file will be overwritten, so hopefully this doesn't introduce any corruption.
     *
     * @return True if the database was successfully written, false if there
     * was an IOException.
     */
    public boolean saveDB() {
        try {
            FileOutputStream outFile = new FileOutputStream("people.db");
            ObjectOutputStream pWriter = new ObjectOutputStream(outFile);
            pWriter.writeObject(personDB);
            pWriter.close();
            return true;
        } catch (IOException e1) {
            System.err.println("Error " + e1);
            return false;
        }
    }

    /**
     * Attempts to read in from the database file called <code>people.db</code>
     * and load each Vehicle's info into the runtime database.
     *
     * @return True if the file existed and the information was successfully
     * read into the ArrayList <code>vehicleDB</code>, false otherwise.
     */
    boolean readDB() {
        try {
            InputStream file = new FileInputStream("people.db");
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput sc = new ObjectInputStream(buffer);
            personDB = (ArrayList<Person>) sc.readObject();
            return true;
        } catch (IOException e1) {
            System.err.println("Error " + e1);
            return false;
        } catch (ClassNotFoundException e2) {
            System.err.println("Error" + e2);
            return false;
        }
    }

}