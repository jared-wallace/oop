/**
 * @(#)Asg1.java
 *
 * Asg1 application
 *
 * @author 
 * @version 1.00 2014/8/28
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
	    		case 3:if(console.deleteCar("boo"))
	    				{
	    					out.println("Car was added successfully");
	    				} 
	    				else
	    					out.println("Car was not added successfully");
	    					break;
	    	    case 4:
	    	    case 5:
	    		case 6: loopControl = false; out.println("bye"); break;
	    		default: out.println("Not an option");
	    	}   
	    	out.println(); 	
	    	out.println();
    	}

    }
   
    
}
