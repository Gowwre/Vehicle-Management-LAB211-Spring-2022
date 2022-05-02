package lab211.assignment;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
* This is comment, do not delete 2021.11.31
 * and open the template in the editor.
 */
/**
 *
 * @author Hoa Doan
 */
public class VehicleManagement {

    public static void main(String args[]) {
        I_Menu menu = new Menu();
        I_List list = new VehicleList();
        // add menu here
        menu.addItem("Add vehicle");
        menu.addItem("Remove vehicle");
        menu.addItem("Update vehicle");
        menu.addItem("Search vehicle");
        menu.addItem("Show vehicle list");
        menu.addItem("Save to file");
        menu.addItem("Load from file");
        menu.addItem("Exit");
        int choice;
        boolean cont = false;

        do {
            menu.showMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    //Adds new vehicle to the list
                    list.addVehicle();
                    break;
                case 2:
                    //Deletes a vehicle when the user enters its ID
                    list.deleteVehicle();
                    break;
                case 3:
                    //Updates a vehicle when the user enters its ID, null fields means old data is left unchanged
                    list.updateVehicle();
                    break;
                case 4:
                    //Searches a vehicle based on its ID, or a group of vehicles based on their name descending by price
                    list.searchVehicle();
                    break;
                // your code here
                case 5:
                    //Shows all the vehicles' information 
                    list.showVehicleList();
                    break;
                case 6:
                    //Saves the vehicles list to a file 
                    list.saveToFile();
                    break;
                case 7:
                    //Loads vehicles from the file
                    list.loadDataFromFile();
                    break;
                case 8:
                    //Asks the user if they want to exit the program
                    cont = menu.confirmYesNo("Do you want to quit?(Y/N)");
                    break;
            }
        } while (!cont);
    }
}
