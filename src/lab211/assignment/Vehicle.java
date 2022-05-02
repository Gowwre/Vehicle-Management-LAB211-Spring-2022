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
public abstract class Vehicle{
    private  String ID;
    private  String name;
    private  String color;
    private  double price;
    private  String brand;

    public Vehicle() {
    }

    
    
    public Vehicle(String ID, String name, String color, double price, String brand) {
        this.ID = ID;
        this.name = name;
        this.color = color;
        this.price = price;
        this.brand = brand;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return  ID + "," + name + "," + brand + "," + color + "," + price; //To change body of generated methods, choose Tools | Templates.
    }

    
   
    
}
