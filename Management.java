/**
 * @(#)Management.java
 *
 *
 * @author 
 * @version 1.00 2014/8/30
 */
import java.util.ArrayList;

public class Management 
{
	private static ArrayList<Car> db;
	
    public Management() 
    {
    	db = new ArrayList<Car>();
    }
    
    public ArrayList<Car> getCars()
    {
    	return db;
    }
    
    public boolean addCar(String lp, String make, String model, int year, double price)
    {
        if(Car.validateLP(lp) && Car.validateMake(make) && Car.validateModel(model) && Car.validateYear(year) && Car.validatePrice(price))
    	    return db.add(new Car(lp, make, model, year,price));
    	return false;
    }
    
    public boolean deleteCar(String lp)
    {
        int i = search(lp);
        if(i == -1)
            return false;
        db.remove(i);
    	return true;
    }
    
    public int search(String s)
    {
        for(int x=0; x<db.size(); x++)
            if(s.equals(db.get(x)))
    	        return x;
    	return -1;
    }
    
    public ArrayList<Car> showPriceRange()
    {
    	return new ArrayList<Car>();
    }
}