package resources.CarInventoryManagement;
/**
 * @(#)Console.java
 *
 *
 * @author Mason Egger and Jared Wallace
 * @version %I%, %G%
 */

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.*;

class Console {

    private final VehicleManager vehicleManager;
    private final PersonManager userManager;

    /**
     * The default constructor for the console class does two basic things. It declares a new
     * instance of the <code>VehicleManager</code> class called <code>vehicleManager</code>, and
     * a new instance of the <code>PersonManager</code> class called <code>userManager</code>.
     */
    private Console() {
        vehicleManager = new VehicleManager();
        userManager = new PersonManager();
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
                    if (console.updateUser(kb)) {
                        out.println("User updated successfully.");
                    } else {
                        err.println("User not updated.");
                    }
                    break;
                case 7:
                    console.showUsers();
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

    boolean addUser(Scanner kb) {
        return userManager.addUser(kb);
    }

    boolean updateUser(Scanner kb) {
        return userManager.updateUser(kb);
    }

    void showUsers() {
        if (userManager.getPersonDB().size() == 0) {
            out.println("Database is empty");
        } else {
            out.println("UID | Last name | First name | Classification | Extra");
            out.println("--------------------------------------------------------------------------------");
            for (Person s : userManager.getPersonDB()) {
                out.println(s);
            }
        }
    }

    boolean sellVehicle(Scanner kb) {
        return vehicleManager.sellVehicle(kb, userManager);
    }

    void showCars() {
        if (vehicleManager.getVehicleDB().size() == 0) {
            out.println("Database is empty");
        } else {
            out.println("Vin Make Model Year Price Mileage Extra");
            out.println("--------------------------------------------------------------------------------");
            for (Vehicle s : vehicleManager.getVehicleDB()) {
                out.println(s);
            }
        }
    }

    boolean addVehicle(Scanner kb) {
        return vehicleManager.addVehicle(kb);
    }

    boolean deleteCar(Scanner kb) {
        out.println("Enter the VIN number of the car to delete.");
        String vin = kb.next();
        return vehicleManager.deleteVehicle(vin);
    }

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
        ArrayList<Vehicle> results = vehicleManager.showPriceRange(lower, higher, type);
        if (results.size() > 0) {
            for (Vehicle s : results) {
                out.println(s);
            }
        } else {
            out.println("Sorry, no matching vehicles found");
        }
    }

    void writeDatabase() {
        if (!userManager.saveDB()) {
            out.println("Unable to save people database.");
        } else {
            out.println("People database updated, exiting...");
        }

        if (!vehicleManager.saveVehicleDB()) {
            out.println("Unable to save vehicle database.");
        } else {
            out.println("Vehicle database updated, exiting...");
        }

        if (!vehicleManager.saveSalesDB()) {
            out.println("Unable to save sales database.");
        } else {
            out.println("Sales database updated, exiting...");
        }
    }

}