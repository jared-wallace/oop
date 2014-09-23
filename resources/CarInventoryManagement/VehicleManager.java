package resources.CarInventoryManagement;
/**
 * @(#)VehicleManager.java
 *
 * @author Mason Egger
 * @author Jared Wallace
 * @version %I%, %G%
 */

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.err;
import static java.lang.System.out;

class VehicleManager {
    private static ArrayList<Vehicle> vehicleDB;
    private static ArrayList<Sale> saleDB;

    /**
     * Default constructor creates an ArrayList of Vehicle objects called <code>vehicleDB</code>.
     * This ArrayList will serve as the runtime database.
     */
    public VehicleManager() {

        vehicleDB = new ArrayList<Vehicle>();
        saleDB = new ArrayList<Sale>();

        if (!readDB()) {
            err.println("Vehicle database corrupted or doesn't exist");
        } else {
            out.println("Successfully read database vehicles.db");
        }

        if (!readSaleDB()) {
            err.println("Sales database corrupted or doesn't exist");
        } else {
            out.println("Successfully read database sales.db");
        }


    }

    public ArrayList<Vehicle> getVehicleDB() {
        return vehicleDB;
    }

    boolean validateVin(String vin) {
        for (Vehicle s : vehicleDB) {
            if (s.getVin().equals(vin))
                return true;
        }
        return false;
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

        make = Character.toUpperCase(make.charAt(0)) + make.substring(1).toLowerCase();
        model = Character.toUpperCase(model.charAt(0)) + model.substring(1).toLowerCase();

        if (type.matches("[cC][aA][rR][sS]?")) {
            return addCar(kb, vin, make, model, year, price, mileage);
        } else if (type.matches("[tT][rR][uU][cC][kK][sS]?")) {
            return addTruck(kb, vin, make, model, year, price, mileage);
        } else
            return type.matches("[mM]") && addMotorcycle(kb, vin, make, model, year, price, mileage);
    }

    /**
     * Adds a car to the runtime database, (assuming all the fields validate correctly).
     * This does not mean that the information will necessarily end up in the database
     * file! The database file is only written upon exiting the program normally!
     *
     * @param vin   The vin number of the new car.
     * @param make  Make of the new car
     * @param model Model of the new car
     * @param year  Year of the new car, can't be before cars were invented, in 1896.
     * @param price Price of the new car, can't be less than 0.00.
     * @return True if the car was successfully added, false otherwise.
     */
    private boolean addCar(Scanner kb, String vin, String make, String model, int year, double price, int mileage) {
        String bodyStyle;
        out.println("Enter the body style.");
        kb.nextLine();
        bodyStyle = kb.nextLine();
        return Car.validateVin(vin) && Car.validateMake(make) && Car.validateModel(model) && Car.validateYear(year)
                && Car.validatePrice(price) && Car.validateBodyStyle(bodyStyle)
                && vehicleDB.add(new Car(vin, make, model, year, price, mileage, bodyStyle));
    }

    /**
     * Adds a truck to the runtime database, (assuming all the fields validate correctly).
     * This does not mean that the information will necessarily end up in the database
     * file! The database file is only written upon exiting the program normally!
     *
     * @param vin   The vin number of the new truck.
     * @param make  Make of the new truck
     * @param model Model of the new truck
     * @param year  Year of the new truck, can't be before cars were invented, in 1896.
     * @param price Price of the new truck, can't be less than 0.00.
     * @return True if the truck was successfully added, false otherwise.
     */
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

        return Truck.validateVin(vin) && Truck.validateMake(make) && Truck.validateModel(model) && Truck.validateYear(year)
                && Truck.validatePrice(price) && Truck.validateMaxLoadWeight(maxLoadWeight)
                && Truck.validateLengthFT(lengthFT)
                && vehicleDB.add(new Truck(vin, make, model, year, price, mileage, maxLoadWeight, lengthFT));
    }

    /**
     * Adds a motorcycle to the runtime database, (assuming all the fields validate correctly).
     * This does not mean that the information will necessarily end up in the database
     * file! The database file is only written upon exiting the program normally!
     *
     * @param vin   The vin number of the new truck.
     * @param make  Make of the new Motorcycle
     * @param model Model of the new Motorcycle
     * @param year  Year of the new Motorcycle, can't be before cars were invented, in 1896.
     * @param price Price of the new Motorcycle, can't be less than 0.00.
     * @return True if the Motorcycle was successfully added, false otherwise.
     */
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
        return Motorcycle.validateVin(vin) && Motorcycle.validateMake(make) && Motorcycle.validateModel(model)
                && Motorcycle.validateYear(year) && Motorcycle.validatePrice(price)
                && Motorcycle.validateDisplacement(displacement) && Motorcycle.validateType(type)
                && vehicleDB.add(new Motorcycle(vin, make, model, year, price, mileage, type, displacement));
    }

    /**
     * Deletes a Vehicle from the runtime database, (assuming the Vehicle
     * existed). See cautionary note on the <code>addVehicle</code> method.
     *
     * @param vin The vin number of the Vehicle to be removed from the database
     * @return True if a Vehicle was deleted, false otherwise
     */
    public boolean deleteVehicle(String vin) {
        if (!Car.validateVin(vin)) {
            return false;
        }
        int i = search(vin);
        if (i == -1) {
            return false;
        }
        vehicleDB.remove(i);
        return true;
    }

    boolean sellVehicle(Scanner kb, PersonManager userManager) {
        int employeeUID = 0;
        int customerUID = 0;
        String vin;
        Date saleDate = null;
        SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");
        double salePrice = 0.0;
        boolean valid = false;

        out.println("Please enter the customers UID");
        while (!valid) {
            try {
                customerUID = kb.nextInt();
                valid = true;
                if (!userManager.validateCustomerUID(customerUID)) {
                    err.println("Error: That UID does not match any customer.");
                    err.println("Please try again.");
                    valid = false;
                }
            } catch (InputMismatchException e1) {
                err.println("Error: That number was not valid.");
                err.println("Please try again.");
                kb.next();
            }
        }

        out.println("Please enter the salesman's UID");
        valid = false;
        while (!valid) {
            try {
                employeeUID = kb.nextInt();
                valid = true;
                if (!userManager.validateEmployeeUID(employeeUID)) {
                    err.println("Error: That UID does not match any employee.");
                    err.println("Please try again.");
                    valid = false;
                }
            } catch (InputMismatchException e1) {
                err.println("Error: That number was not valid.");
                err.println("Please try again.");
                kb.next();
            }
        }

        out.println("Please enter the VIN number of the car being sold.");
        vin = kb.next();
        valid = false;
        while (!valid) {
            if (validateVin(vin)) {
                valid = true;
            } else {
                err.println("Error: That VIN does not match any existing vehicle in the database.");
                err.println("Please re-enter the VIN number.");
                vin = kb.next();
            }
        }
        out.println("Please enter the date of sale (Format MM/dd/yyyy)");
        valid = false;
        while (!valid) {
            try {
                saleDate = fmt.parse(kb.next());
                valid = true;
            } catch (ParseException e1) {
                err.println("Error: Please try again");
                kb.next();
            }
        }
        out.println("Please enter the final sale price.");
        valid = false;
        while (!valid) {
            try {
                salePrice = kb.nextDouble();
                valid = true;
            } catch (InputMismatchException e1) {
                err.println("Error: That number was invalid.");
                err.println("Please try again.");
                kb.next();
            }
        }

        deleteVehicle(vin);
        return saleDB.add(new Sale(employeeUID, customerUID, vin, saleDate, salePrice));
    }

    /**
     * Given a vin number, attempts to locate the Vehicle in question in the
     * runtime database.
     *
     * @param s A string that is the vin number of the vehicle being sought.
     * @return The index of the matching vehicle, if found. Otherwise returns -1.
     */
    int search(String s) {
        for (int x = 0; x < vehicleDB.size(); x++) {
            String vin = vehicleDB.get(x).getVin();
            if (s.equals(vin)) {
                return x;
            }
        }
        return -1;
    }

    /**
     * Allows filtering of the runtime database by a price range.
     *
     * @param lower  The lower limit of the price range to be searched.
     * @param higher The upper limit of the price range to be searched.
     * @param type   The type of results to return (car, truck, motorcycle or all)
     * @return An ArrayList of Vehicles falling within the specified price range.
     */
    public ArrayList<Vehicle> showPriceRange(double lower, double higher, String type) {
        ArrayList<Vehicle> results = new ArrayList<Vehicle>();
        for (Vehicle s : vehicleDB) {
            if (s.getPrice() > lower && s.getPrice() < higher) {
                if (type.equals("cars") && s instanceof Car) {
                    results.add(s);
                } else if (type.equals("trucks") && s instanceof Truck) {
                    results.add(s);
                } else {
                    results.add(s);
                }
            }
        }
        return results;
    }

    /**
     * Attempts to write the existing runtime vehicle database to the database file.
     * The file will be overwritten, so hopefully this doesn't introduce any corruption.
     *
     * @return True if the database was successfully written, false if there
     * was an IOException.
     */
    public boolean saveVehicleDB() {
        try {
            FileOutputStream outFile = new FileOutputStream("vehicles.db");
            ObjectOutputStream pWriter = new ObjectOutputStream(outFile);
            pWriter.writeObject(vehicleDB);
            pWriter.close();
            return true;
        } catch (IOException e1) {
            System.err.println("Error " + e1);
            return false;
        }
    }

    /**
     * Attempts to write the existing sales runtime database to the database file.
     * The file will be overwritten, so hopefully this doesn't introduce any corruption.
     *
     * @return True if the database was successfully written, false if there
     * was an IOException.
     */
    public boolean saveSalesDB() {
        try {
            FileOutputStream outFile = new FileOutputStream("sales.db");
            ObjectOutputStream pWriter = new ObjectOutputStream(outFile);
            pWriter.writeObject(saleDB);
            pWriter.close();
            return true;
        } catch (IOException e1) {
            System.err.println("Error " + e1);
            return false;
        }
    }

    /**
     * Attempts to read in from the database file called <code>Vehicles.txt</code>
     * and load each Vehicle's info into the runtime database.
     *
     * @return True if the file existed and the information was successfully
     * read into the ArrayList <code>vehicleDB</code>, false otherwise.
     */
    boolean readDB() {
        try {
            InputStream file = new FileInputStream("vehicles.db");
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput sc = new ObjectInputStream(buffer);
            vehicleDB = (ArrayList<Vehicle>) sc.readObject();
            return true;
        } catch (IOException e1) {
            return false;
        } catch (ClassNotFoundException e2) {
            return false;
        }
    }

    /**
     * Attempts to read in from the database file called <code>sales.db</code>
     * and load each Vehicle's info into the runtime database.
     *
     * @return True if the file existed and the information was successfully
     * read into the ArrayList <code>salesDB</code>, false otherwise.
     */
    boolean readSaleDB() {
        try {
            InputStream file = new FileInputStream("sales.db");
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput sc = new ObjectInputStream(buffer);
            saleDB = (ArrayList<Sale>) sc.readObject();
            return true;
        } catch (IOException e1) {
            return false;
        } catch (ClassNotFoundException e2) {
            return false;
        }
    }
}