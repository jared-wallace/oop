/**
 * @(#)Management.java
 *
 * @author Mason Egger
 * @author Jared Wallace
 * @version %I%, %G%
 */
import java.util.ArrayList;

public class Management 
{
	private static ArrayList<Car> db;
	
    public Management() 
    {
    	db = new ArrayList<Car>();
    }

    /**
     *
     * @return Returns the existing database.
     */
    public ArrayList<Car> getCars()
    {
    	return db;
    }

    /**
     *
     * @param lp The license plate of the new car. (Format XXX-XXX only)
     * @param make Can't be blank.
     * @param model Can't be blank.
     * @param year Can't be before cars were invented, in 1896.
     * @param price Can't be less than free.
     * @return
     */
    public boolean addCar(String lp, String make, String model, int year, double price)
    {
        if(Car.validateLP(lp) && Car.validateMake(make) && Car.validateModel(model) && Car.validateYear(year) && Car.validatePrice(price))
    	    return db.add(new Car(lp, make, model, year,price));
    	return false;
    }

    /**
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
     *
     * @param s Accepts a string that is the license plate of the car being sought.
     * @return Returns the index of the matching car, if found. Otherwise returns -1.
     */
    public int search(String s)
    {
        for(int x=0; x<db.size(); x++)
            if(s.equals(db.get(x)))
    	        return x;
    	return -1;
    }

    /**
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

    public boolean saveDB()
    {
        return true;
    }

    public boolean readDB()
    {
        return true;
    }
}