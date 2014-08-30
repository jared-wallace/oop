
public class Car 
{
	private String lpNumber, make, model;
	private int year;
	private double price;
	
    public Car(String lpNumber, String make, String model, int year, double price) 
    {
    	if(lpNumber == "" || make == "" || model == "" || year < 0 || price < 0)
    	{
    		//Validation	
    	}
    	this.lpNumber = lpNumber;
    	this.make = make;
    	this.model = model;
    	this.year = year;
    	this.price = price;
    }
    
    //Should we allow default init?
    public Car()
    {
    	this("XXX-XXX","Empty","Empty",0,0.0);
    }
    
    public static boolean validateLP(String lp)
    {
        if(lp.matches("[\\w]{3}-[\\w]{3}"))
            return true;
        return false;
    }
    
    public static boolean validateMake(String make)
    {
        if(make.equals(""))
            return false;
        return true;
    }
    
    public static boolean validateModel(String model)
    {
        if(model.equals(""))
            return false;
        return true;
    }
    
    public static boolean validateYear(int year)
    {
        if(year < 1886)
            return false;
        return true;
    }
    
    public static boolean validatePrice(double price)
    {
        if(price < 0.0)
            return false;
        return true;
        
    }

    public double getPrice()
    {
        return price;
    }
    
    //didn't do anything specital with formatting. Do that later
    public String toString()
    {
    	return lpNumber + " " + make + " " + model + " " + year + " " + price;
    }
    
    
}