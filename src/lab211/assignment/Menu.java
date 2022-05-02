package lab211.assignment;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * This is comment, do not delete 2021.11.30
 * and open the template in the editor.
 */
/**
 *
 * @author USER
 */
import java.util.ArrayList;

//Menu class extends ArrayList class and implements I_Menu
public class Menu extends ArrayList<String> implements I_Menu {

    

    public Menu() {
        super();
    }
    // must implement all abstract method of I_Menu interface

    @Override
    public void addItem(String s) {
        // do not delete this comment, your code here:
        //add string to menu
        this.add(s);
        
    }

    @Override
    public void showMenu() {
        // do not delete this comment, your code here:
        //print out all the menu items
        for (int i = 0; i < this.size(); i++) {
            System.out.println((i+1) + "." + this.get(i));
        }

    }

    @Override
    public boolean confirmYesNo(String welcome) {
        boolean result = false;
        //result is the return value of the confirmYesNo() static method
        result = Utils.confirmYesNo(welcome);
        return result;
    }

    @Override
    public int getChoice() {
        //return the integer value of the value you input
        //min value is 1, max value is the size of menu
        return Utils.getInt("Input your choice:", 1, this.size());
    }

}
