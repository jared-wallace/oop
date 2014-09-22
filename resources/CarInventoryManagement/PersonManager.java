package resources.CarInventoryManagement;

import java.io.*;
import java.util.*;
import static java.lang.System.*;

/**
 * Created by jared on 9/21/14.
 */
class PersonManager {

    private static ArrayList<Person> personDB;

    /**
     * Default constructor creates an ArrayList of Person objects called <code>personDB</code>.
     * This ArrayList will serve as the runtime database.
     */
    public PersonManager() {
        personDB = new ArrayList<Person>();
    }

    /**
     * Simply a way to retrieve the existing database held in memory.
     *
     * @return The ArrayList <code>personDB</code>
     */
    public ArrayList<Person> getPersons() {
        return personDB;
    }

    /**
     * Adds an employee to the runtime database, (assuming all the fields validate correctly).
     * This does not mean that the information will necessarily end up in the database
     * file! The database file is only written upon exiting the program normally!
     *
     * @param iD            The vin number of the new car.
     * @param firstName     Make of the new car
     * @param lastName      Model of the new car
     * @param salary        Year of the new car, can't be before cars were invented, in 1896.
     * @param accountNumber Price of the new car, can't be less than 0.00.
     * @return True if the employee was successfully added, false otherwise.
     */
    public boolean addPerson(int iD, String firstName, String lastName, double salary, int accountNumber) {
        return Person.validateID(iD) && Person.validateName(firstName) && Person.validateName(lastName)
                && Employee.validateSalary(salary) && Employee.validateAccountNumber(accountNumber)
                && personDB.add(new Employee(iD, firstName, lastName, salary, accountNumber));
    }

    /**
     * Adds a customer to the runtime database, (assuming all the fields validate correctly).
     * This does not mean that the information will necessarily end up in the database
     * file! The database file is only written upon exiting the program normally!
     *
     * @param iD          The vin number of the new truck.
     * @param firstName   Make of the new truck
     * @param lastName    Model of the new truck
     * @param phoneNumber Year of the new truck, can't be before cars were invented, in 1896.
     * @param dLNumber    Price of the new truck, can't be less than 0.00.
     * @return True if the customer was successfully added, false otherwise.
     */
    public boolean addPerson(int iD, String firstName, String lastName, String phoneNumber, int dLNumber) {
        return Person.validateID(iD) && Person.validateName(firstName) && Person.validateName(lastName)
                && Customer.validatePhoneNumber(phoneNumber) && Customer.validateDriversLicense(dLNumber)
                && personDB.add(new Customer(iD, firstName, lastName, phoneNumber, dLNumber));
    }

    /**
     * Given a UID, attempts to locate the Vehicle in question in the
     * runtime database.
     *
     * @param s An int that is the UID of the person being sought.
     * @return The index of the matching person, if found. Otherwise returns -1.
     */
    public int search(int s) {
        for (int x = 0; x < personDB.size(); x++) {
            int UID = personDB.get(x).getID();
            if (s == UID) {
                return x;
            }
        }
        return -1;
    }


	public static int getUID(ArrayList<Person> personDB)
	{
        if (personDB.size() == 0) {
            return 1;
        }
		return (personDB.get(personDB.size()-1)).getID() + 1;
	}

    public static boolean sellCar(int customerUID, int employeeUID, String vin, Date saleDate, double salePrice) {
        return;
    }
    /**
     * Attempts to write the existing runtime database to the database file.
     * The file will be overwritten, so hopefully this doesn't introduce any corruption.
     *
     * @return True if the database was successfully written, false if there
     * was an IOException.
     */
    public boolean saveDB() {
        try {
            FileOutputStream outFile = new FileOutputStream("people.db");
            ObjectOutputStream pWriter = new ObjectOutputStream(outFile);
            pWriter.writeObject(personDB);
            pWriter.close();
            return true;
        } catch (IOException e1) {
            System.err.println("Error " + e1);
            return false;
        }
    }

    /**
     * Attempts to read in from the database file called <code>people.db</code>
     * and load each Vehicle's info into the runtime database.
     *
     * @return True if the file existed and the information was successfully
     * read into the ArrayList <code>vehicleDB</code>, false otherwise.
     */
    public boolean readDB() {
        try {
            InputStream file = new FileInputStream("people.db");
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput sc = new ObjectInputStream(buffer);
            personDB = (ArrayList<Person>) sc.readObject();
            return true;
        } catch (IOException e1) {
            System.err.println("Error " + e1);
            return false;
        } catch (ClassNotFoundException e2) {
            System.err.println("Error" + e2);
            return false;
        }
    }
    
    boolean updateUser(Scanner kb) {
        
        int userID = -1;
        boolean valid = false;
        
        out.println("Enter the id of the User");
        
        while(!valid){
            try{
                userID = kb.nextInt();
                valid = true;
            }
            catch(InputMismatchException e){
                err.println("Not valid input");
                err.println("Please input the id of the User again");
                kb.next();
            }
        }
        valid = false;
        
        if(userID < 0 || userID > personDB.size()-1)
        {
            err.println("User does not exist");
            return false;
        }
        else
        {
            if(personDB.get(userID) instanceof Customer)
            {
                int choice = -1;
                String changeS = "";
                int changeI = -1;
                double changeD = -1;
                out.println("Enter the number of the field you want to change");
                out.println("\t1. First Name");
                out.println("\t2. Last Name");
                out.println("\t3. Phone Number");
                out.println("\t4. Drivers License Number");
                out.println("\t5. Cancel");
                
                while(!valid){
                    try{
                        choice = kb.nextInt();
                        valid = true;
                    }
                    catch(InputMismatchException e){
                        err.println("Input was not an integer");
                        err.println("Please reenter the option you wish to change. ");
                        kb.next();
                    }
                }
                
                switch(choice)
                {
                    case 1:
                        out.println("Enter your first name");
                        changeS = kb.next();
                        if(Person.validateName(changeS))
                        {
                            personDB.get(userID).setFirstName(changeS);
                            return true;
                        }
                        else
                        {
                            out.println("Invalid name.");
                            return false;
                        }
                    case 2:
                        out.println("Enter your last name");
                        changeS = kb.next();
                        if(Person.validateName(changeS))
                        {
                            personDB.get(userID).setLastName(changeS);
                            return true;
                        }
                        else
                        {
                            out.println("Invalid name.");
                            return false;
                        }
                    case 3:
                        out.println("Enter your phone number");
                        changeS = kb.next();
                        if(Customer.validatePhoneNumber(changeS))
                        {
                            ((Customer)personDB.get(userID)).setPhoneNumber(changeS);
                            return true;
                        }
                        else
                        {
                            out.println("Invalid phone number.");
                            return false;
                        }
                    case 4:
                        out.println("Enter your drivers license");
                        changeI = kb.nextInt();
                        if(Customer.validateDriversLicense(changeI))
                        {
                            ((Customer)personDB.get(userID)).setDriversLicense(changeI);
                            return true;
                        }
                        else
                        {
                            out.println("Invalid drivers license");
                            return false;
                        }
                    case 5:
                        return false;
                    
                    default:
                        out.println("Not a correct choice.");
                }
                
            }
            else
            {
                
            }
        }
    }

}