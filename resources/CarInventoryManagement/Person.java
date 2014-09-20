package resources.CarInventoryManagement;
/**
 * created by Mason Egger - 9/19
 */


abstract class Person 
{

    private int id;
    private String fName, lName;
    
    public Person(int id, String fName, String lName) 
    {
        this.id = id;
        this.fName=fName;
        this.lName=lName;
    }
    
    public static boolean validateName(String name)
    {
        return !(name.length()<1);
    }
    
    public static boolean validateID(int id)
    {
        return !(id < 0);
    }
    
    public void setID(int id)
    {
        this.id = id;
    }
    
    public int getID()
    {
        return id;
    }
    
    public void setfName(String fName)
    {
        this.fName = fName;
    }
    
    public String getfName()
    {
        return fName;
    }
    
    public void setlName(String lName)
    {
        this.lName = lName;
    }
    
    public String getlName()
    {
        return lName;
    }
    
    @Override
    public String toString()
    {
        return this.getID() + " " + this.getlName() + "," + this.getfName();
    }
}
