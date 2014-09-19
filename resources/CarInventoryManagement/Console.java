package resources.CarInventoryManagement;
/**
 * @(#)Console.java
 *
 *
 * @author Mason Egger and Jared Wallace
 * @version %I%, %G%
 */

import static java.lang.System.*;

import java.util.*;

class Console {

    private final Management manage;
    private final ArrayList<Vehicle> db;

    public static void main(String[] args) {
        Scanner kb = new Scanner(in);
        Console console = new Console();
        boolean loopControl = true;
        while (loopControl) {
            console.printMenu();
            switch (kb.nextInt()) {
                case 1:
                    console.showCars();
                    break;
                case 2:
                    if (console.addCar(kb)) {
                        out.println("Car was added successfully");
                    } else
                        out.println("Car was not added successfully");
                    break;
                case 3:
                    if (console.deleteCar(kb)) {
                        out.println("Car was added successfully");
                    } else
                        out.println("Car was not added successfully");
                    break;
                case 4:
                    console.searchCar(kb);
                    break;
                case 5:
                    console.showPriceRange(kb);
                    break;
                case 6:
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

    /**
     * The default constructor for the console class does a few different things. It declares a new
     * instance of the <code>Management</code> class called <code>manage</code>, and then proceeds
     * to attempt reading the existing car database, called <code>cars.txt</code>. If that file fails to be
     * read/parsed correctly, it prints an error message to that effect. Otherwise, it prints a line
     * indicating that the database has been successfully loaded into memory. Lastly, it calls the
     * <code>Management.getCars</code> method to get the new arraylist, called <code>db</code>
     */
    private Console() {
        manage = new Management();
        if (manage.readDB())
            err.println("Database corrupted or doesn't exist");
        else
            out.println("Successfully read database cars.txt");
        db = manage.getVehicles();
    }

    /**
     * Prints the menu with all the currently available options.
     */
    public void printMenu() {
        out.println("1. Show all existing car records in the database (in any order).");
        out.println("2. Add a new car record to the database.");
        out.println("3. Delete a car record from a database.");
        out.println("4. Search for a car (given its license plate).");
        out.println("5. Show a list of cars within a given price range.");
        out.println("6. Exit program.");

    }


    /**
     * Prints out the current database. This will print what is currently
     * in memory, but not necessarily what is currently written to the file.
     */
    public void showCars() {
        if (db.size() == 0)
            out.println("Database is empty");
        else {
            out.println("Vin Make Model Year Price Mileage Body Style Maximum Load Weight(lb) Length(ft)");
            out.println("---------------------------");
            for (Vehicle s : db)
                out.println(s);
        }
    }

    /**
     * Attempts to add a new car to the database in memory. This only succeeds
     * if all the fields successfully pass validation.
     *
     * @param kb The scanner object used to read in from the console
     * @return True if a car was successfully added to the database in memory,
     * false if something went wrong.
     */
    public boolean addCar(Scanner kb) {
        String vin, make, model, bodyStyle;
        int year, mileage;
        double price;

        out.println("Enter the vin number.");
        kb.nextLine();
        vin = kb.nextLine();
        out.println("Enter the make. (Cannot be blank)");
        make = kb.next();
        out.println("Enter the model. (Cannot be blank)");
        model = kb.next();
        try {
            out.println("Enter the year. (Cannot be < 1886)");
            year = kb.nextInt();
        } catch (InputMismatchException e) {
            err.println("Input entered was not of Integer type.");
            kb.nextLine();
            return false;
        }

        try {
            out.println("Enter the price. (Cannot be < 0)");
            price = kb.nextDouble();
        } catch (InputMismatchException e) {
            err.println("Input entered was not a number.");
            kb.nextLine();
            return false;
        }

        try {
            out.println("Enter the mileage. (Cannot be negative)");
            mileage = kb.nextInt();
        } catch (InputMismatchException e) {
            err.println("Input entered was not of Integer type.");
            kb.nextLine();
            return false;
        }

        out.println("Enter the body style.");
        kb.nextLine();
        bodyStyle = kb.nextLine();
        return manage.addVehicle(vin, make, model, year, price, mileage, bodyStyle);

    }

    /**
     * Attempts to delete a car from the database in memory. The car is searched for
     * by license plate number. This does not necessarily mean the change is permanently
     * written to the database file.
     *
     * @param kb The scanner object used to read in from the console
     * @return True if the car was located and deleted, false otherwise
     */
    public boolean deleteCar(Scanner kb) {
        out.println("Enter the vin number of the car to delete.");
        String vin = kb.next();
        if (Car.validateVin(vin)) {
            return manage.deleteVehicle(vin);
        } else {
            out.println("Invalid vin number entered");
            return false;
        }
    }

    /**
     * Allows searching the database in memory for a certain car by license plate number
     *
     * @param kb The scanner object used to read in from the console
     */
    public void searchCar(Scanner kb) {
        out.println("Enter the license plate of the car you wish to search for.");
        String lp = kb.next();
        int index = manage.search(lp);
        if (index > -1)
            out.println(db.get(index));
        else
            out.println("Sorry, no matching vehicle found.");
    }

    /**
     * Allows searching the database in memory for cars whose prices fall within a specified
     * range.
     *
     * @param kb The scanner object used to read in from the console
     */
    public void showPriceRange(Scanner kb) {
        out.println("Enter the lower limit of the price range you wish to search.");
        double lower = kb.nextDouble();
        out.println("Enter the higher limit of the range you wish to search");
        double higher = kb.nextDouble();
        out.println("Enter the type of vehicle you wish to search for (cars, trucks, motorcycles or all)");
        String choice = kb.next();
        String type;
        if (choice.matches("[cC]ar[sS]?") )
            type = "cars";
        else if (choice.matches("[mM]ot[eo]r\\s?([cC]ycle|[bB]ike)"))
            type = "motorcycles";
        else if (choice.matches("[tT]ruck[sS]?"))
            type = "trucks";
        else
            type = "all";
        ArrayList<Vehicle> results = manage.showPriceRange(lower, higher, type);
        if (results.size() > 0)
            for (Vehicle s : results)
                out.println(s);
        else
            out.println("Sorry, no matching vehicles found");
    }

    /**
     * Attempts to write the current database in memory to a file called cars.txt
     * as plain text. If this effort fails, it will indicate so with an error message.
     * If it succeeds, it will likewise indicate.
     */
    public void writeDatabase() {
        if (manage.saveDB())
            out.println("Unable to save database.");
        else
            out.println("Database updated, exiting...");
    }
}