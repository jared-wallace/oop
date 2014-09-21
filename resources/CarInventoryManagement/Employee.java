package resources.CarInventoryManagement;
/**
 * created by Mason Egger - 9/20
 */




public class Employee extends Person
{
	private double salary;
	private int bankNum;
	
    public Employee(int id, String fName, String lName, double salary, int bankNum) 
    {
    	super(id, fName, lName);
    	this.salary = salary;
    	this.bankNum = bankNum;
    }
    
    public static boolean validateSalary(double salary)
    {
    	return !(salary < 0.0);
    }
    
    public static boolean validateBankAccount(int bankNum)
    {
    	return !(bankNum < 0);
    }
    
    public void setBankAccount(int bankNum)
    {
    	this.bankNum = bankNum;
    }
    
    public void setSalary(double salary)
    {
    	this.salary = salary;
    }
    
    public int getBankAccount()
    {
    	return bankNum;
    }
    
    public double getSalary()
    {
    	return salary;
    }
    
    @Override
    public String toString()
    {
    	return super.toString() + " " + this.getSalary() + " " + this.getBankAccount();
    }
    
}