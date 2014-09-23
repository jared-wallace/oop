package resources.CarInventoryManagement;
/**
 * @(#)Sale.java
 *
 *
 * @author Mason Egger
 * @author Jared Wallace
 * @version %I%, %G%
 */

import java.io.Serializable;
import java.util.Date;


class Sale implements Serializable {
    private int customerUID;
    private int employeeUID;
    private String vin;
    private Date saleDate;
    private double salePrice;

    /**
     * The constructor for the Sale class. This class exists only for the
     * purpose of creating new Sale records.
     *
     * @param employeeUID1 The employee's UID who is selling the vehicle
     * @param customerUID1 The customer's UID who is purchasing the vehicle
     * @param vin1 The VIN number of the vehicle being sold
     * @param saleDate1 The date of the sale
     * @param salePrice1 The final sale price of the vehicle
     */
    public Sale(int employeeUID1, int customerUID1, String vin1, Date saleDate1, double salePrice1) {
        employeeUID = employeeUID1;
        customerUID = customerUID1;
        vin = vin1;
        saleDate = saleDate1;
        salePrice = salePrice1;
    }

}
