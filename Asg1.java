/**
 * @(#)Asg1.java
 *
 * Asg1 application
 *
 * @author 
 * @version 1.00 2014/8/28
 */
 import static java.lang.System.*;
 import java.util.*;
 
public class Asg1 
{    
   public static void main(String[] args) 
   {    	
      ArrayList<Car> ray = new ArrayList<Car>();
		
		//test case
      ray.add(new Car("Stuff","Toyota","Corola",2006,554.5));    	
			
      Scanner kb = new Scanner(in);
      boolean loopControl = true;
      while(loopControl)
      {
         printMenu();
         switch(kb.nextInt())
         {
	    case 1: showCars(ray); break;
	    case 2: if(addCar(ray, kb))
                    {
                       out.println("Car was added successfully");
                    } 
                    else
                       out.println("Car was not added successfully");
                       break;
	    case 3:if(deleteCar(ray, kb))
                   {
                      out.println("Car was added successfully");
                   }   
                   else
                      out.println("Car was not added successfully");
                      break;
	    case 4:
	    case 5:
            case 6: loopControl = false; out.println("bye"); break;
         }    	
      }
    }
    
    //haven't decided if these should be in Main or in a Management class
    static void printMenu()
    {
    	out.println("1. Show all existing car records in the database (in any order).");
    	out.println("2. Add a new car record to the database.");
    	out.println("3. Delete a car record from a database.");
    	out.println("4. Search for a car (given its license plate).");
    	out.println("5. Show a list of cars within a given price range.");
    	out.println("6. Exit program.\n\n");
    	
    }
    
    static void showCars(ArrayList<Car> ray)
    {
    	for(Car s : ray)
    	   out.println(s);
    }
    
    static boolean addCar(ArrayList<Car> ray, Scanner kb)
    {
    	out.println("Input the license plate number, make, model, year, price");
    	return ray.add(new Car(kb.next(),kb.next(),kb.next(),kb.nextInt(),kb.nextDouble()));
    }
    
    static boolean deleteCar(ArrayList<Car> ray, Scanner kb)
    {
    	return true;
    }
    
    static boolean search(ArrayList<Car> ray)
    {
    	return true;
    }
    
    static ArrayList<Car> showPriceRange(ArrayList<Car> ray)
    {
    	return new ArrayList<Car>();
    }
    
}
