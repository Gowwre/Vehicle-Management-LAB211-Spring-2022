/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab211.assignment;



/**
 *
 * @author Radriar
 */
public class Car extends Vehicle{

    private String type;
    private int yearOfManufacture;

    public Car(String type, int yearOfManufacture, String ID, String name, String color, double price, String brand) {
        super(ID, name, color, price, brand);
        this.type = type;
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    
    @Override
    public String toString() {
        return super.getID() + "," + super.getName() + "," + super.getBrand() + "," + super.getColor() + "," + super.getPrice() + "," + type + "," + yearOfManufacture; //To change body of generated methods, choose Tools | Templates.
    }


    

    

}
