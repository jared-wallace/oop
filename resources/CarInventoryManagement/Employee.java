package resources.CarInventoryManagement;
/**
 * created by Mason Egger - 9/20
 */


class Employee extends Person
{
	private double salary;
	private int bankNumber;
	
    public Employee(int id, String firstName, String lastName, double salary, int bankNumber)
    {
    	super(id, firstName, lastName);
    	this.salary = salary;
    	this.bankNumber = bankNumber;
    }
    
    public static boolean validateSalary(double salary)
    {
    	return !(salary < 0.0);
    }

    /**
     * The type of accounts we are interested in validating (ACH capable)
     * are limited to numeric only, between 4 and 17 characters.
     *
     * @param bankNumber The employee account number to validate.
     * @return True if the account number is an integer between 4 and 17 digits
     */
    public static boolean validateBankAccount(int bankNumber)
    {
    	return !(String.valueOf(bankNumber).length() < 4 && String.valueOf(bankNumber).length() > 17);
    }
    
    public void setBankAccount(int bankNumber)
    {
    	this.bankNumber = bankNumber;
    }
    
    public void setSalary(double salary)
    {
    	this.salary = salary;
    }
    
    int getBankAccount()
    {
    	return bankNumber;
    }
    
    double getSalary()
    {
    	return salary;
    }
    
    @Override
    public String toString()
    {
    	return super.toString() + " " + this.getSalary() + " " + this.getBankAccount();
    }
    
}