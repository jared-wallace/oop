package resources.CarInventoryManagement;
/**
 * created by Mason Egger - 9/19
 */


public class Motorcycle extends Vehicle
{
    private String type;
    private int displacement;
    
    public Motorcycle(String vin, String make, String model, int year, double price, int mileage, String type, int displacement) 
    {
        super(vin,make,model,price,mileage,year);
        this.setType(type);
        this.setDisplacement(displacement);
    }
    
    
    
    /**
     * Checks to see if the type is blank since there is no set list of types.
     *
     * @param type - the type of motorcycle (Scooter, Touring, Street Bike, etc.)
     * @return True if the type is not blank
     */
    public static boolean validateType(String type)
    {
       return !(type.equals(""));
    }
    
    /**
     * Checks to see if the displacement is less than 0
     *
     * @param displacement - integer value representing displacement
     * @return True if the displacement is not less than 0
     */
    public static boolean validateDisplacement(int displacement)
    {
        //I have no clue what displacement is, so if there is another way we should check this let me know. 
        return !(displacement < 0);
    }
    
    /**
     * Adds displacement and type to the default Vehicle string.
     *
     * @return A nicely formatted string with all the Motorcycle fields.
     */
    @Override
    public String toString()
    {
        String orig = super.toString();
        return orig + this.getDisplacement() + this.getType();
    }
    
    public int getDisplacement()
    {
        return displacement;
    }

    public void setDisplacement(int displacement)
    {
        this.displacement = displacement;
    }
    
    public String getType()
    {
        return type;
    }
    
    public void setType(String type)
    {
        this.type = type;
    }
}