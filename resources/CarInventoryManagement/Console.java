package resources.CarInventoryManagement;
/**
 * @(#)Console.java
 *
 *
 * @author Mason Egger and Jared Wallace
 * @version %I%, %G%
 */

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.*;

class Console {

    private final VehicleManager manage;
    private final PersonManager userManager;
    private final ArrayList<Vehicle> vehiclesDB;
    private final ArrayList<Person> personDB;
    /**
     * The default constructor for the console class does a few different things. It declares a new
     * instance of the <code>VehicleManager</code> class called <code>manage</code>, and then proceeds
     * to attempt reading the existing vehicle database, called <code>vehicle.txt</code>. If that file fails to be
     * read/parsed correctly, it prints an error message to that effect. Otherwise, it prints a line
     * indicating that the database has been successfully loaded into memory. Lastly, it calls the
     * <code>VehicleManager.getVehicles</code> method to get the new ArrayList, called <code>vehiclesDB</code>
     */
    private Console() {
        manage = new VehicleManager();
        userManager = new PersonManager();

        if (!userManager.readDB()) {
            err.println("Database corrupted or doesn't exist");
        } else {
            out.println("Successfully read database people.db");
        }
        personDB = userManager.getPersons();

        if (!manage.readDB()) {
            err.println("Database corrupted or doesn't exist");
        } else {
            out.println("Successfully read database vehicles.db");
        }
        vehiclesDB = manage.getVehicles();
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(in);
        Console console = new Console();
        boolean loopControl = true;
        while (loopControl) {
            console.printMenu();
            switch (kb.nextInt()) {
                case 1:
                    if (console.addVehicle(kb)) {
                        out.println("Car was added successfully");
                    } else {
                        out.println("Error: Car was not added successfully");
                    }
                    break;
                case 2:
                    if (console.deleteCar(kb)) {
                        out.println("Car was added successfully");
                    } else {
                        out.println("Error: Car was not added successfully");
                    }
                    break;
                case 3:
                    console.showCars();
                    break;
                case 4:
                    console.showPriceRange(kb);
                    break;
                case 5:
                    if (console.addUser(kb)) {
                        out.println("User added successfully");
                    } else {
                        out.println("Error: User not added successfully");
                    }
                    break;
                case 6:
                    console.updateUser(kb);
                    break;
                case 7:
                    console.showUsers(kb);
                    break;
                case 8:
                    if (console.sellVehicle(kb)) {
                        out.println("Vehicle sold.");
                    } else {
                        out.println("Error: Vehicle not sold.");
                    }
                    break;
                case 9:
                    loopControl = false;
                    console.writeDatabase();
                    break;
                default:
                    out.println("Not an option");
            }
            out.println();
            out.println();
        }
    }

    boolean addUser(Scanner kb) {
        int uID = -1;
        String lastName, firstName, type;

        out.println("Please enter the type of user you wish to add. (employee or customer)");
        type = kb.next();
        if (type.matches("[cC]\\w*"))
            type = "customer";
        else
            type = "employee";

        uID = PersonManager.getUID(personDB);

        out.println("Enter the first name of the user.");
        firstName = kb.next();
        out.println("Enter the last name of the user.");
        lastName = kb.next();

        if (type.equals("employee")) {
            return addEmployee(kb, uID, lastName, firstName);
        }
        else {
            return addCustomer(kb, uID, lastName, firstName);
        }
    }

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

        return userManager.addPerson(uID, firstName, lastName, salary, accountNumber);
    }

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
        return userManager.addPerson(uID, firstName, lastName, phoneNumber, dLNumber);
    }

    boolean updateUser(Scanner kb) {
        return false;
    }

    void showUsers(Scanner kb) {

    }

    boolean sellVehicle(Scanner kb) {
        return false;
    }

    /**
     * Prints the menu with all the currently available options.
     */
    void printMenu() {
        out.println("1. Add a new vehicle to the database.");
        out.println("2. Delete a vehicle from a database (given its VIN).");
        out.println("3. Show all existing vehicles in the database.");
        out.println("4. Show a list of vehicles within a given price range.");
        out.println("5. Add a new user to the database.");
        out.println("6. Update user info (given their id).");
        out.println("7. Show list of users.");
        out.println("8. Sell a vehicle.");
        out.println("9. Exit program.");

    }


    /**
     * Prints out the current database. This will print what is currently
     * in memory, but not necessarily what is currently written to the file.
     */
    void showCars() {
        if (vehiclesDB.size() == 0) {
            out.println("Database is empty");
        } else {
            out.println("Vin Make Model Year Price Mileage Extra");
            out.println("--------------------------------------------------------------------------------");
            for (Vehicle s : vehiclesDB) {
                out.println(s);
            }
        }
    }

    /**
     * Attempts to add a new vehicle to the database in memory. This only succeeds
     * if all the fields successfully pass validation.
     *
     * @param kb The scanner object used to read in from the console
     * @return True if a vehicle was successfully added to the database in memory,
     * false if something went wrong.
     */
    boolean addVehicle(Scanner kb) {
        String vin, make, model, type;
        int year = -1;
        int mileage = -1;
        double price = -1.0;
        boolean valid = false;
        out.println("Please enter the type of vehicle (car, truck or motorcycle");
        type = kb.next();

        out.println("Enter the vin number.");
        vin = kb.next();
        out.println("Enter the make. (Cannot be blank)");
        make = kb.next();
        out.println("Enter the model. (Cannot be blank)");
        model = kb.next();

        out.println("Enter the year (cannot be prior to 1886)");
        while (!valid) {
            try {
                year = kb.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                err.println("That was not an integer value.");
                err.println("Please enter the year again.");
                kb.next();
            }
        }

        out.println("Enter the price. (Cannot be < 0)");
        valid = false;
        while (!valid) {
            try {
                price = kb.nextDouble();
                valid = true;
            } catch (InputMismatchException e) {
                err.println("The input was not a valid number.");
                err.println("Please enter the price again.");
                kb.next();
            }
        }

        out.println("Enter the mileage. (Cannot be negative)");
        valid = false;
        while (!valid) {
            try {
                mileage = kb.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                err.println("The input was not a valid number.");
                err.println("Please enter the mileage again");
                kb.next();
            }
        }

        if (type.matches("[cC][aA][rR][sS]?")) {
            return addCar(kb, vin, make, model, year, price, mileage);
        } else if (type.matches("[tT][rR][uU][cC][kK][sS]?")) {
            return addTruck(kb, vin, make, model, year, price, mileage);
        } else
            return type.matches("[mM]") && addMotorcycle(kb, vin, make, model, year, price, mileage);
    }

    private boolean addCar(Scanner kb, String vin, String make, String model, int year, double price, int mileage) {
        String bodyStyle;
        out.println("Enter the body style.");
        kb.nextLine();
        bodyStyle = kb.nextLine();
        return manage.addVehicle(vin, make, model, year, price, mileage, bodyStyle);
    }

    private boolean addTruck(Scanner kb, String vin, String make, String model, int year, double price, int mileage) {
        int maxLoadWeight = -1;
        double lengthFT = -1.0;
        boolean valid = false;

        out.println("Enter the maximum load weight in pounds");
        while (!valid) {
            try {
                maxLoadWeight = kb.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                err.println("The input was not a valid number.");
                err.println("Please enter the load weight again.");
                kb.next();
            }
        }

        out.println("Enter the length of the truck in feet");
        valid = false;
        while (!valid) {
            try {
                lengthFT = kb.nextDouble();
                valid = true;
            } catch (InputMismatchException e) {
                err.println("The input was not a valid number.");
                err.println("Please enter the length again.");
                kb.next();
            }
        }

        return manage.addVehicle(vin, make, model, year, price, mileage, maxLoadWeight, lengthFT);
    }

    private boolean addMotorcycle(Scanner kb, String vin, String make, String model, int year, double price, int mileage) {
        String type;
        int displacement;

        out.println("Enter the type of Motorcycle");
        type = kb.next();
        out.println("Enter the displacement");
        try {
            displacement = kb.nextInt();
        } catch (InputMismatchException e) {
            err.println("The input was not a valid number.");
            return false;
        }
        return manage.addVehicle(vin, make, model, year, price, mileage, displacement, type);
    }

    /**
     * Attempts to delete a vehicle from the database in memory. The vehicle is located
     * by VIN number. This does not necessarily mean the change is permanently
     * written to the database file.
     *
     * @param kb The scanner object used to read in from the console
     * @return True if the vehicle was located and deleted, false otherwise
     */
    boolean deleteCar(Scanner kb) {
        out.println("Enter the VIN number of the car to delete.");
        String vin = kb.next();
        if (Car.validateVin(vin)) {
            return manage.deleteVehicle(vin);
        } else {
            out.println("Invalid vin number entered");
            return false;
        }
    }

    /**
     * Allows searching the database in memory for cars whose prices fall within a specified
     * range.
     *
     * @param kb The scanner object used to read in from the console
     */
    void showPriceRange(Scanner kb) {
        out.println("Enter the lower limit of the price range you wish to search.");
        double lower = kb.nextDouble();
        out.println("Enter the higher limit of the range you wish to search");
        double higher = kb.nextDouble();
        out.println("Enter the type of vehicle you wish to search for (cars, trucks, motorcycles or all)");
        String choice = kb.next();
        String type;
        if (choice.matches("[cC]ar[sS]?")) {
            type = "cars";
        } else if (choice.matches("[mM]ot[eo]r\\s?([cC]ycle|[bB]ike)")) {
            type = "motorcycles";
        } else if (choice.matches("[tT]ruck[sS]?")) {
            type = "trucks";
        } else {
            type = "all";
        }
        ArrayList<Vehicle> results = manage.showPriceRange(lower, higher, type);
        if (results.size() > 0) {
            for (Vehicle s : results) {
                out.println(s);
            }
        } else {
            out.println("Sorry, no matching vehicles found");
        }
    }

    /**
     * Attempts to write the current database in memory to a file called cars.txt
     * as plain text. If this effort fails, it will indicate so with an error message.
     * If it succeeds, it will likewise indicate.
     */
    void writeDatabase() {
        if (!manage.saveDB()) {
            out.println("Unable to save database.");
        } else {
            out.println("Database updated, exiting...");
        }
    }
}