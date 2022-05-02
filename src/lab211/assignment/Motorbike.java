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
public class Motorbike extends Vehicle{
    private  int speed;
    private  boolean isLicenseRequired;

    public Motorbike(int speed, boolean isLicenseRequired, String ID, String name, String color, double price, String brand) {
        super(ID, name, color, price, brand);
        this.speed = speed;
        this.isLicenseRequired = isLicenseRequired;
    }

    

    

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean getIsLicenseRequired() {
        return isLicenseRequired;
    }

    public void setIsLicenseRequired(boolean isLicenseRequired) {
        this.isLicenseRequired = isLicenseRequired;
    }

    
    @Override
    public String toString() {
        return super.getID() + "," + super.getName()+"," + super.getBrand()   + "," + super.getColor() + "," + super.getPrice() +"," + speed + "," + isLicenseRequired; //To change body of generated methods, choose Tools | Templates.
    }
    public void makeSound(){
        System.out.println("Tin tin tin");
    }
}
