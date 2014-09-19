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
import java.util.Scanner;

class Management
{
	private static ArrayList<Car> carsdb;

    /**
     * Default constructor creates an ArrayList of Car objects called <code>carsdb</code>.
     * This ArrayList will serve as the runtime database.
     */
    public Management() 
    {
    	carsdb = new ArrayList<Car>();
    }

    /**
     * Simply a way to retrieve the existing database held in memory.
     *
     * @return The ArrayList <code>carsdb</code>
     */
    public ArrayList<Car> getCars()
    {
    	return carsdb;
    }

    /**
     * Adds a car to the runtime database, (assuming all the fields validate correctly).
     * This does not mean that the information will necessarily end up in the database
     * file! The database file is only written upon exiting the program normally!
     *
     * @param vin The license plate of the new car.
     * @param make Make of the new car
     * @param model Model of the new car
     * @param year Year of the new car, can't be before cars were invented, in 1896.
     * @param price Price of the new car, can't be less than free.
     * @return True if the car was successfully added, false otherwise.
     */
    public boolean addCar(String vin, String make, String model, int year, double price, int mileage, String bodyType)
    {
        if(Car.validateVin(vin) && Car.validateMake(make) && Car.validateModel(model) && Car.validateYear(year) && Car.validatePrice(price))
        {
            make = Character.toUpperCase(make.charAt(0)) + make.substring(1).toLowerCase();
            model = Character.toUpperCase(model.charAt(0)) + model.substring(1).toLowerCase();
            bodyType = Character.toUpperCase(bodyType.charAt(0)) + bodyType.substring(1).toLowerCase();
            return carsdb.add(new Car(vin, make, model, year, price, mileage, bodyType));
        }
    	return false;
    }

    /**
     * Deletes a car from the runtime database, (assuming the car
     * existed). See cautionary note on the <code>addCar</code> method.
     *
     * @param lp The license plate of the car to be removed from the database
     * @return True if a car was deleted, false otherwise
     */
    public boolean deleteCar(String lp)
    {
        int i = search(lp);
        if(i == -1)
            return false;
        carsdb.remove(i);
    	return true;
    }

    /**
     * Given a license plate, attempts to locate the car in question in the
     * runtime database.
     *
     * @param s Accepts a string that is the license plate of the car being sought.
     * @return The index of the matching car, if found. Otherwise returns -1.
     */
    public int search(String s)
    {
        for(int x=0; x< carsdb.size(); x++)
        {
            String lp = carsdb.get(x).getVin();
            if (s.equals(lp))
                return x;
        }
    	return -1;
    }

    /**
     * Allows filtering of the runtime database by a price range.
     *
     * @param lower The lower limit of the price range to be searched.
     * @param higher The upper limit of the price range to be searched.
     * @return An ArrayList of cars falling within the specified price range.
     */
    public ArrayList<Car> showPriceRange(double lower, double higher)
    {
        ArrayList<Car> results = new ArrayList<Car>();
        for(Car s: carsdb)
            if(s.getPrice() > lower && s.getPrice() < higher )
                results.add(s);
    	return results;
    }

    /**
     * Attempts to write the existing runtime database to the database file.
     * The file will be overwritten, so hopefully this doesn't introduce any corruption.
     *
     * @return True if the database was successfully written, false if there
     * was an IOException.
     */
    public boolean saveDB()
    {
        try
        {
            FileWriter outFile = new FileWriter ("cars.txt");
            PrintWriter pWriter = new PrintWriter(outFile);
            for (Car s: carsdb)
            {
                pWriter.println(s);
            }
            pWriter.close();
            return false;
        }
        catch(IOException e1)
        {
            return true;
        }
    }

    /**
     * Attempts to read in from the database file called <code>cars.txt</code>
     * and load each car's info into the runtime database.
     *
     * @return True if the file existed and the information was successfully
     * read into the ArrayList <code>carsdb</code>, false otherwise.
     */
    public boolean readDB()
    {
        try
        {
            File inFile = new File ("cars.txt");
            Scanner sc = new Scanner (inFile);
            while (sc.hasNext())
            {
                String vin = sc.next();
                String make = sc.next();
                String model = sc.next();
                int year = sc.nextInt();
                double price = sc.nextDouble();
                int mileage = sc.nextInt();
                String bodyType = sc.next();
                addCar(vin, make, model, year, price, mileage, bodyType);
            }
            sc.close();
            return false;
        }
        catch(IOException e1)
        {
            return true;
        }
    }
}