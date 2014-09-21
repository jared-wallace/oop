package resources.CarInventoryManagement;

/**
 * created by Mason Egger - 9/20
 */


class Employee extends Person {
    private double salary;
    private int accountNumber;

    public Employee(int id, String firstName, String lastName, double salary, int accountNumber) {
        super(id, firstName, lastName);
        this.salary = salary;
        this.accountNumber = accountNumber;
    }

    public static boolean validateSalary(double salary) {
        return !(salary < 0.0);
    }

    /**
     * The type of accounts we are interested in validating (ACH capable)
     * are limited to numeric only, between 4 and 17 characters.
     *
     * @param accountNumber The employee account number to validate.
     * @return True if the account number is an integer between 4 and 17 digits
     */
    public static boolean validateAccountNumber(int accountNumber) {
        return !(String.valueOf(accountNumber).length() < 4 && String.valueOf(accountNumber).length() > 17);
    }

    int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.getSalary() + " " + this.getAccountNumber();
    }

}