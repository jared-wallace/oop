
public class Car 
{
	private String lpNumber, make, model;
	private int year;
	private double price;
	
    public Car(String lpNumber, String make, String model, int year, double price) 
    {
    	if(lpNumber.equals("") || make.equals("") || model.equals("") || year < 0 || price < 0)
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

    /**
     * The validation function allows any combination of numbers and
     * letters, as well as periods, spaces and dashes. This is in
     * accordance with TxDOT's current rule set for license plates.
     * Unfortunately, TxDOT also allows texas symbols and heart symbols,
     * neither of which can be entered. Instead, the texas symbol should
     * be entered as "*" and the heart as "@".
     *
     * @param lp The license plate number to be checked for validity.
     * @return Returns true for a valid plate, false otherwise.
     */
    public static boolean validateLP(String lp)
    {
        if(lp.matches("[\\w\\./[ ]\\*@]{1,7}"))
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