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

public class Management 
{
	private static ArrayList<Car> db;

    /**
     * Default constructor creates an ArrayList of Car objects called <code>db</code>
     */
    public Management() 
    {
    	db = new ArrayList<Car>();
    }

    /**
     * Simply a way to retrieve the existing ArrayList <code>db</code>
     *
     * @return Returns the existing database.
     */
    public ArrayList<Car> getCars()
    {
    	return db;
    }

    /**
     * Adds a car to the ArrayList <code>db</code>, (assuming
     * all the fields validate correctly), which serves as the runtime database.
     * This does not mean that the information will necessarily end up in the database
     * file! The database file is only written upon exiting the program normally.
     *
     * @param lp The license plate of the new car. (Format XXX-XXX only)
     * @param make Can't be blank.
     * @param model Can't be blank.
     * @param year Can't be before cars were invented, in 1896.
     * @param price Can't be less than free.
     * @return Returns true if the car was successfully added, false otherwise.
     */
    public boolean addCar(String lp, String make, String model, int year, double price)
    {
        if(Car.validateLP(lp) && Car.validateMake(make) && Car.validateModel(model) && Car.validateYear(year) && Car.validatePrice(price))
        {
            make = Character.toUpperCase(make.charAt(0)) + make.substring(1).toLowerCase();
            model = Character.toUpperCase(model.charAt(0)) + model.substring(1).toLowerCase();
            return db.add(new Car(lp, make, model, year,price));
        }
    	return false;
    }

    /**
     * Deletes a car from the ArrayList <code>db</code>, (assuming
     * it existed), which serves as the runtime database. See <code>addCar</code> for
     * the difference between runtime and file databases.
     *
     *
     * @param lp The license plate of the car to be removed from the database
     * @return Returns true if a car was deleted, false otherwise
     */
    public boolean deleteCar(String lp)
    {
        int i = search(lp);
        if(i == -1)
            return false;
        db.remove(i);
    	return true;
    }

    /**
     * Given a license plate, attempts to locate the car in question in the
     * runtime database.
     *
     * @param s Accepts a string that is the license plate of the car being sought.
     * @return Returns the index of the matching car, if found. Otherwise returns -1.
     */
    public int search(String s)
    {
        for(int x=0; x<db.size(); x++)
        {
            String lp = db.get(x).getLpNumber();
            if (s.equals(lp))
                return x;
        }
    	return -1;
    }

    /**
     * Allows filtering of the database by a price range.
     *
     * @param lower The lower limit of the price range to be searched.
     * @param higher The upper limit of the price range to be searched.
     * @return Returns an ArrayList of cars falling within the specified price range.
     */
    public ArrayList<Car> showPriceRange(double lower, double higher)
    {
        ArrayList<Car> results = new ArrayList<Car>();
        for(Car s: this.db)
            if(s.getPrice() > lower && s.getPrice() < higher )
                results.add(s);
    	return results;
    }

    /**
     * Attempts to write the existing runtime database to the database file.
     * The file will be overwritten, so hopefully this doesn't introduce any corruption.
     *
     * @return Returns true if the database was successfully written, false if there
     * was an IOException.
     */
    public boolean saveDB()
    {
        try
        {
            FileWriter outFile = new FileWriter ("resources/CarInventoryManagement/cars.txt");
            PrintWriter pWriter = new PrintWriter(outFile);
            for (Car s: this.db)
            {
                pWriter.println(s);
            }
            pWriter.close();
            return true;
        }
        catch(IOException e1)
        {
            return false;
        }
    }

    /**
     * Attempts to read in from the database file called <code>car_database.db</code>
     * and load each car's info into the runtime database.
     *
     * @return Returns true if the file existed and the information was successfully
     * read into the ArrayList <code>db</code>, false otherwise.
     */
    public boolean readDB()
    {
        try
        {
            File inFile = new File ("resources/CarInventoryManagement/cars.txt");
            Scanner sc = new Scanner (inFile);
            while (sc.hasNext())
            {
                String lp = sc.next();
                String make = sc.next();
                String model = sc.next();
                int year = sc.nextInt();
                double price = sc.nextDouble();
                addCar(lp, make, model, year, price);
            }
            sc.close();
            return true;
        }
        catch(IOException e1)
        {
            return false;
        }
    }
}