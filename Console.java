/**
 * @(#)Console.java
 *
 *
 * @author Mason Egger and Jared Wallace
 * @version %I%, %G%
 */
import static java.lang.System.*;
import java.util.Scanner;
import java.util.ArrayList;


public class Console {

    private Management manage;
    private ArrayList<Car> db;
    public Console() 
    {
        manage = new Management();
        if (!manage.readDB())
            out.println("Database corrupted or doesn't exist");
        else
            out.println("Successfully read database cars.txt");
        db = manage.getCars();
    }
    
    public void printMenu()
    {
        out.println("1. Show all existing car records in the database (in any order).");
        out.println("2. Add a new car record to the database.");
        out.println("3. Delete a car record from a database.");
        out.println("4. Search for a car (given its license plate).");
        out.println("5. Show a list of cars within a given price range.");
        out.println("6. Exit program.");
        
    }


    public void showCars()
    {
        if(db.size()==0)
            out.println("Database is empty");
        else
        {
            out.println("Plate Make Model Year Price");
            out.println("---------------------------");
            for (Car s : db)
                out.println(s);
        }
    }

    /**
     *
     * @param kb
     * @return
     */
    public boolean addCar(Scanner kb)
    {
        String lp, make, model;
        int year;
        double price;
        
        out.println("Enter the license plate number.");
        kb.nextLine();
        lp = kb.nextLine();
        out.println("Enter the make. (Cannot be blank)");
        make = kb.next();
        out.println("Enter the model. (Cannot be blank)");
        model=kb.next();
        out.println("Enter the year. (Cannot be < 1886)");
        year = kb.nextInt();
        out.println("Enter the price. (Cannot be < 0)");
        price = kb.nextDouble();
        
        return manage.addCar(lp, make, model, year, price);
        
    }

    /**
     *
     * @param kb
     * @return
     */
    public boolean deleteCar(Scanner kb)
    {
        out.println("Enter the license plate of the car to delete.");
        String lp = kb.next();
        if(Car.validateLP(lp))
        {
            return manage.deleteCar(lp);
        }
        else
        {
            out.println("Invalid license plate entered");
            return false;
        }
    }

    /**
     *
     * @param kb
     */
    public void searchCar(Scanner kb)
    {
        out.println("Enter the license plate of the car you wish to search for.");
        String lp = kb.next();
        int index = manage.search(lp);
        if (index > -1)
            out.println(db.get(index));
        else
            out.println("Sorry, no matching vehicle found.");
    }

    /**
     *
     * @param kb
     */
    public void showPriceRange(Scanner kb)
    {
        out.println("Enter the lower limit of the price range you wish to search.");
        double lower = kb.nextDouble();
        out.println("Enter the higher limit of the range you wish to search");
        double higher = kb.nextDouble();
        ArrayList<Car> results = manage.showPriceRange(lower, higher);
        if (results.size() > 0)
            for(Car s : results)
                out.println(s);
        else
            out.println("Sorry, no matching vehicles found");
    }

    /**
     * Writes the current ArrayList db to a file called cars.txt
     * as plain text.
     */
    public void writeDatabase()
    {
        if(!manage.saveDB())
            out.println("Unable to save database.");
        else
            out.println("Database updated, exiting...");
    }
}