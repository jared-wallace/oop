package resources.CarInventoryManagement;

import java.util.Date;

/**
 * Created by jared on 9/21/14.
 */
public class Sale {
    private int employeeUID;
    private int customerUID;
    private String vin;
    private Date saleDate;
    private double salePrice;

    public Sale(int employeeUID, int customerUID, String vin, Date saleDate, double salePrice) {
        this.employeeUID = employeeUID;
        this.customerUID = customerUID;
        this.vin = vin;
        this.saleDate = saleDate;
        this.salePrice = salePrice;
    }

    boolean validateEmployeeUID(int employeeUID) {
        for (int x = 0; x < personManager.searchsize(); x++) {
            String vin = vehicleDB.get(x).getVin();
            if (s.equals(vin)) {
                return x;
            }
        }
        return -1;
    }

    int getEmployeeUID() {
        return employeeUID;
    }

    void setEmployeeUID(int employeeUID) {
        this.employeeUID = employeeUID;
    }

    int getCustomerUID() {
        return customerUID;
    }

    void setCustomerUID(int customerUID) {
        this.customerUID = customerUID;
    }

    String getVin() {
        return vin;
    }

    void setVin(String vin) {
        this.vin = vin;
    }

    int getSaleDate() {
        return saleDate;
    }

    void setSaleDate(int saleDate) {
        this.saleDate = saleDate;
    }

    double getSalePrice() {
        return salePrice;
    }

    void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }
}
