package resources.CarInventoryManagement;
/**
 * created by Mason Egger - 9/19
 */


public class Customer extends Person
{
    private int dlNumber;
    private String phoneNumber;
    
    public Customer(int id, String fName, String lName, String phoneNumber, int dlNumber) 
    {
        super(id, fName, lName);
        this.setPhoneNumber(phoneNumber);
        this.setDriversLicense(dlNumber);
    }
    
    public static boolean validatePhoneNumber(String phoneNumber)
    {
        //validate phone numbers of format "1234567890"
        if (phoneNumber.matches("\\d{10}"))
           return true;
        //validating phone number with -, . or spaces
        else if(phoneNumber.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) 
           return true;
        //validating phone number with extension length from 3 to 5
        else if(phoneNumber.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}"))
           return true;
        //validating phone number where area code is in braces ()
        else //return false if nothing matches the input
            return phoneNumber.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}");
    }
    
    public static boolean validateDriversLicense(int dlNumber)
    {
       //every state has a different setup for license numbers
       //since the requirements specify int we are going to go with 
       //Texas Drivers license which is 7-8 ints long  

        return !(dlNumber < 1000000 || dlNumber > 99999999);
    }
    
    void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    
    void setDriversLicense(int dlNumber)
    {
        this.dlNumber = dlNumber;
    }
    
    String getPhoneNumber()
    {
        return phoneNumber;
    }
    
    int getDriversLicense()
    {
        return dlNumber;
    }
       
    @Override   
    public String toString()
    {
        return super.toString() + this.getPhoneNumber() + " " + this.getDriversLicense();
    } 
}