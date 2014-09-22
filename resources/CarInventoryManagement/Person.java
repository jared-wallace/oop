package resources.CarInventoryManagement;

/**
 * created by Mason Egger - 9/19
 */


abstract class Person{

    private int id;
    private String firstName, lastName;

    Person(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static boolean validateName(String name) {
        return !(name.length() < 1);
    }

    public static boolean validateID(int id) {
        return !(id < 0);
    }

    int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return this.getID() + " " + this.getLastName() + "," + this.getFirstName();
    }
    
}
