package resources.CarInventoryManagement;
/**
 * @(#)Management.java
 *
 * @author Mason Egger
 * @author Jared Wallace
 * @version %I%, %G%
 */

import java.io.*;
import java.util.ArrayList;

class Management
{
    private static ArrayList<Vehicle> vehicleDB;

    /**
     * Default constructor creates an ArrayList of Vehicle objects called <code>vehicleDB</code>.
     * This ArrayList will serve as the runtime database.
     */
    public Management() {
        vehicleDB = new ArrayList<Vehicle>();
    }

    /**
     * Simply a way to retrieve the existing database held in memory.
     *
     * @return The ArrayList <code>vehicleDB</code>
     */
    public ArrayList<Vehicle> getVehicles() {
        return vehicleDB;
    }

    /**
     * Adds a car to the runtime database, (assuming all the fields validate correctly).
     * This does not mean that the information will necessarily end up in the database
     * file! The database file is only written upon exiting the program normally!
     *
     * @param vin       The vin number of the new car.
     * @param make      Make of the new car
     * @param model     Model of the new car
     * @param year      Year of the new car, can't be before cars were invented, in 1896.
     * @param price     Price of the new car, can't be less than 0.00.
     * @param bodyStyle The type of car it is.
     * @return True if the car was successfully added, false otherwise.
     */
    public boolean addVehicle(String vin, String make, String model, int year, double price,
                              int mileage, String bodyStyle) {
        if (Car.validateVin(vin) && Car.validateMake(make) && Car.validateModel(model) && Car.validateYear(year)
                && Car.validatePrice(price) && Car.validateBodyStyle(bodyStyle)) {
            make = Character.toUpperCase(make.charAt(0)) + make.substring(1).toLowerCase();
            model = Character.toUpperCase(model.charAt(0)) + model.substring(1).toLowerCase();
            return vehicleDB.add(new Car(vin, make, model, year, price, mileage, bodyStyle));
        }
        return false;
    }

    /**
     * Adds a truck to the runtime database, (assuming all the fields validate correctly).
     * This does not mean that the information will necessarily end up in the database
     * file! The database file is only written upon exiting the program normally!
     *
     * @param vin           The vin number of the new truck.
     * @param make          Make of the new truck
     * @param model         Model of the new truck
     * @param year          Year of the new truck, can't be before cars were invented, in 1896.
     * @param price         Price of the new truck, can't be less than 0.00.
     * @param maxLoadWeight The maximum load rating in pounds of the truck.
     * @param lengthFT      The length in feet of the truck.
     * @return True if the truck was successfully added, false otherwise.
     */
    public boolean addVehicle(String vin, String make, String model, int year, double price, int mileage,
                              int maxLoadWeight, double lengthFT) {
        if (Truck.validateVin(vin) && Truck.validateMake(make) && Truck.validateModel(model) && Truck.validateYear(year)
                && Truck.validatePrice(price) && Truck.validateMaxLoadWeight(maxLoadWeight)
                && Truck.validateLengthFT(lengthFT)) {
            make = Character.toUpperCase(make.charAt(0)) + make.substring(1).toLowerCase();
            model = Character.toUpperCase(model.charAt(0)) + model.substring(1).toLowerCase();
            return vehicleDB.add(new Truck(vin, make, model, year, price, mileage, maxLoadWeight, lengthFT));
        }
        return false;
    }


    /**
     * Adds a motorcycle to the runtime database, (assuming all the fields validate correctly).
     * This does not mean that the information will necessarily end up in the database
     * file! The database file is only written upon exiting the program normally!
     *
     * @param vin           The vin number of the new truck.
     * @param make          Make of the new Motorcycle
     * @param model         Model of the new Motorcycle
     * @param year          Year of the new Motorcycle, can't be before cars were invented, in 1896.
     * @param price         Price of the new Motorcycle, can't be less than 0.00.
     * @param displacement  The displacement of the Motorcycle, can't be less than zero.
     * @param type          The type of the motorcycle (Scooter, Touring, Street Bike, Bitchen, etc)
     * @return True if the Motorcycle was successfully added, false otherwise.
     */
    public boolean addVehicle(String vin, String make, String model, int year, double price, int mileage,
                              int displacement, String type) {
        if (Motorcycle.validateVin(vin) && Motorcycle.validateMake(make) && Motorcycle.validateModel(model) && Motorcycle.validateYear(year)
                && Motorcycle.validatePrice(price) && Motorcycle.validateDisplacement(displacement)
                && Motorcycle.validateType(type)) {
            make = Character.toUpperCase(make.charAt(0)) + make.substring(1).toLowerCase();
            model = Character.toUpperCase(model.charAt(0)) + model.substring(1).toLowerCase();
            return vehicleDB.add(new Motorcycle(vin, make, model, year, price, mileage, type, displacement));
        }
        return false;
    }

    /**
     * Deletes a Vehicle from the runtime database, (assuming the Vehicle
     * existed). See cautionary note on the <code>addVehicle</code> method.
     *
     * @param vin The vin number of the Vehicle to be removed from the database
     * @return True if a Vehicle was deleted, false otherwise
     */
    public boolean deleteVehicle(String vin) {
        int i = search(vin);
        if (i == -1)
            return false;
        vehicleDB.remove(i);
        return true;
    }

    /**
     * Given a vin number, attempts to locate the Vehicle in question in the
     * runtime database.
     *
     * @param s A string that is the vin number of the vehicle being sought.
     * @return The index of the matching vehicle, if found. Otherwise returns -1.
     */
    public int search(String s) {
        for (int x = 0; x < vehicleDB.size(); x++) {
            String vin = vehicleDB.get(x).getVin();
            if (s.equals(vin))
                return x;
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
        for (Vehicle s : vehicleDB){
            if (s.getPrice() > lower && s.getPrice() < higher){
                if (type.equals("cars") && s instanceof Car)
                    results.add(s);
                else if (type.equals("trucks") && s instanceof Truck)
                    results.add(s);
                else
                    results.add(s);
            }
        }
        return results;
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
            FileOutputStream outFile = new FileOutputStream("vehicles.txt");
            ObjectOutputStream pWriter = new ObjectOutputStream(outFile); 
            //for(Vehicle s: vehicleDB)
            pWriter.writeObject(vehicleDB);
            
            pWriter.close();
            return true;
        } catch (IOException e1) {
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
    public boolean readDB() {
        try {
            InputStream file = new FileInputStream("vehicles.txt");
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput sc = new ObjectInputStream(buffer);
            vehicleDB = (ArrayList<Vehicle>)sc.readObject();
            return true;
        } 
        catch (IOException e1) 
        {
            System.err.println("Error " + e1);
            return false;
        }
        catch(ClassNotFoundException e2)
        {
            System.err.println("Error" + e2);
      		return false;
        }
    }
}