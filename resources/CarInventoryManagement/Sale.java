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

/**
 * Created by jared on 9/21/14.
 */
class Sale implements Serializable {
    private int customerUID;
    private int employeeUID;
    private String vin;
    private Date saleDate;
    private double salePrice;

    public Sale(int employeeUID1, int customerUID1, String vin1, Date saleDate1, double salePrice1) {
        employeeUID = employeeUID1;
        customerUID = customerUID1;
        vin = vin1;
        saleDate = saleDate1;
        salePrice = salePrice1;
    }

}
