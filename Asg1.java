/**
 * @(#)Asg1.java
 *
 * Asg1 application
 *
 * @author Mason Egger and Jared Wallace
 * @version %I%, %G%
 */
 import static java.lang.System.*;
 import java.util.Scanner;
 
public class Asg1 
{    
    public static void main(String[] args) 
    {    				
    	Scanner kb = new Scanner(in);
    	Console console = new Console();
    	boolean loopControl = true;
    	while(loopControl)
    	{
	    	console.printMenu();
	    	switch(kb.nextInt())
	    	{
	    		case 1: console.showCars(); break;
	    		case 2: if(console.addCar(kb))
	    				{
	    					out.println("Car was added successfully");
	    				} 
	    				else
	    					out.println("Car was not added successfully");
	    					break;
	    		case 3:if(console.deleteCar(kb))
	    				{
	    					out.println("Car was added successfully");
	    				} 
	    				else
	    					out.println("Car was not added successfully");
	    					break;
	    	    case 4: console.searchCar(kb); break;
	    	    case 5: console.showPriceRange(kb); break;
	    		case 6: loopControl = false;
                        console.writeDatabase();
                        break;
	    		default: out.println("Not an option");
	    	}   
	    	out.println(); 	
	    	out.println();
    	}

    }
   
    
}
