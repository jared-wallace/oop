
public class Car {

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
    
    //didn't do anything specital with formatting. Do that later
    public String toString()
    {
    	return lpNumber + " " + make + " " + model + " " + year + " " + price;
    }
    
    
}