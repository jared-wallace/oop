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
    private final ArrayList<Vehicle> vehiclesDB;

    public static void main(String[] args) {
        Scanner kb = new Scanner(in);
        Console console = new Console();
        try{
            //give our program time to read the database
            Thread.currentThread().sleep(1000);
        }
        catch(InterruptedException ie){
            // interruption causes no harm
        }
        boolean loopControl = true;
        while (loopControl) {
            console.printMenu();
            switch (kb.nextInt()) {
                case 1:
                    if (console.addVehicle(kb)) {
                        out.println("Car was added successfully");
                    } else
                        out.println("Car was not added successfully");
                    break;
                case 2:
                    if (console.deleteCar(kb)) {
                        out.println("Car was added successfully");
                    } else
                        out.println("Car was not added successfully");
                    break;
                case 3:
                    console.showCars();
                    break;
                case 4:
                    console.showPriceRange(kb);
                    break;
                case 5:
                    console.searchCar(kb);
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
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

    /**
     * The default constructor for the console class does a few different things. It declares a new
     * instance of the <code>Management</code> class called <code>manage</code>, and then proceeds
     * to attempt reading the existing vehicle database, called <code>vehicle.txt</code>. If that file fails to be
     * read/parsed correctly, it prints an error message to that effect. Otherwise, it prints a line
     * indicating that the database has been successfully loaded into memory. Lastly, it calls the
     * <code>Management.getVehicles</code> method to get the new ArrayList, called <code>vehiclesDB</code>
     */
    private Console() {
        manage = new Management();
        if (!manage.readDB())
            err.println("Database corrupted or doesn't exist");
        else
            out.println("Successfully read database vehicles.txt");
        vehiclesDB = manage.getVehicles();
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
        if (vehiclesDB.size() == 0)
            out.println("Database is empty");
        else {
            out.println("Vin Make Model Year Price Mileage Extra");
            out.println("--------------------------------------------------------------------------------");
            for (Vehicle s : vehiclesDB)
                out.println(s);
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
        while(!valid) {
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
        while(!valid) {
            try {
                price=kb.nextDouble();
                valid = true;
            }
            catch(InputMismatchException e) {
                err.println("The input was not a valid number.");
                err.println("Please enter the price again.");
                kb.next();
            }
        }

        out.println("Enter the mileage. (Cannot be negative)");
        valid = false;
        while(!valid) {
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
            type = "car";
            return addCar(kb, vin, make, model, year, price, mileage);
        } else if (type.matches("[tT][rR][uU][cC][kK][sS]?")) {
            type = "truck";
            return addTruck(kb, vin, make, model, year, price, mileage);
        } else if (type.matches("[mM]")) {
            type = "motorcycle";
            return addMotorcycle(kb, vin, make, model, year, price, mileage);
        } else {
            return false;
        }
    }

    boolean addCar(Scanner kb, String vin, String make, String model, int year, double price, int mileage) {
        String bodyStyle;
        out.println("Enter the body style.");
        kb.nextLine();
        bodyStyle = kb.nextLine();
        return manage.addVehicle(vin, make, model, year, price, mileage, bodyStyle);
    }

    boolean addTruck(Scanner kb, String vin, String make, String model, int year, double price, int mileage) {
        int maxLoadWeight = -1;
        double lengthFT = -1.0;

        out.println("Enter the maximum load weight in pounds");
        try {
            maxLoadWeight=kb.nextInt();
        }
        catch(InputMismatchException e) {
            err.println("The input was not a valid number.");
            return false;
        }

        out.println("Enter the length of the truck in feet");
        try {
            lengthFT=kb.nextDouble();
        }
        catch(InputMismatchException e) {
            err.println("The input was not a valid number.");
            return false;
        }

        return manage.addVehicle(vin, make, model, year, price, mileage, maxLoadWeight, lengthFT);
    }

    boolean addMotorcycle(Scanner kb, String vin, String make, String model, int year, double price, int mileage) {
        String type;
        int displacement;

        out.println("Enter the type of Motorcycle");
        type = kb.next();
        out.println("Enter the displacement");
        try {
            displacement=kb.nextInt();
        }
        catch(InputMismatchException e) {
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
     * Allows searching the database in memory for a certain vehicle by VIN number
     *
     * @param kb The scanner object used to read in from the console
     */
    void searchCar(Scanner kb) {
        out.println("Enter the VIN number of the vehicle you wish to search for.");
        String lp = kb.next();
        int index = manage.search(lp);
        if (index > -1)
            out.println(vehiclesDB.get(index));
        else
            out.println("Sorry, no matching vehicle found.");
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
    void writeDatabase() {
        if (!manage.saveDB())
            out.println("Unable to save database.");
        else
            out.println("Database updated, exiting...");
    }
}