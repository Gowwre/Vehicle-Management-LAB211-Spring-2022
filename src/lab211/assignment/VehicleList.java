package lab211.assignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * This is comment, do not delete 2021.11.30
 * and open the template in the editor.
 */

/**
 * @author Hoa Doan
 */
public class VehicleList extends ArrayList<Vehicle> implements I_List {

    @Override
    public void loadDataFromFile() {
        //Input file's name
        Scanner sc = new Scanner(System.in);
        System.out.print("Input your desired file's name to load data (File's extension already included): ");
        String filename = sc.nextLine().trim();
        filename = filename + ".txt";
        try {
            //create new file object with the file name argument
            File f = new File(filename);
            //create new FileReader object with a File reference variable as an argument
            FileReader fr = new FileReader(f);
            //create new BufferedReader object with f FileReader reference variable as an argument
            BufferedReader myReader = new BufferedReader(fr);
            //read data when myReader is ready
            while (myReader.ready()) {
                //data is a line in the text file
                String data = myReader.readLine();
                //create an array of string where each index contains a value before the delimiter
                String[] split = data.split(",");
                //general vehicle's fields
                //First string index contains ID
                String ID = split[0];
                //Second string index contains name
                String name = split[1];
                //Third string index contains brand name
                String brand = split[2];
                //Fourth string index contains vehicle's color
                String color = split[3];
                double price;
                try {
                    //Fifth string index contains vehicle's price
                    price = Double.parseDouble(split[4]);
                } catch (Exception e) {
                    price = 0;
                }//motorbike's specific fields

                int speed = 0;
                boolean isLicenseRequired = false;
                //If it is a motorcycle's ID, then the last two string indexes contains speed and license requirement
                if (ID.matches(Utils.MOTO_REGEX)) {
                    speed = Integer.parseInt(split[5]);
                    isLicenseRequired = Boolean.parseBoolean(split[6]);
                }
                //car's specific fields
                String type = null;
                int yearOfManufacture = 0;
                //It is a car's ID, then the last two string indexes contains car's type and year of manufacture
                if (ID.matches(Utils.CAR_REGEX)) {
                    type = split[5];
                    yearOfManufacture = Integer.parseInt(split[6]);
                }

                //if the string matches the ID format and the ID does exist and the price is larger than the minimum
                if (ID.matches(Utils.VEHICLE_REGEX) && !isIDExist(ID) && price > Utils.MIN_PRICE) {
                    //if the ID matches of a cars and the year of manufacture is later than the minimum
                    if (ID.matches(Utils.CAR_REGEX) && yearOfManufacture > Utils.MIN_YEAR) {
                        //then add a new car to this list
                        this.add(new Car(type, yearOfManufacture, ID, name, color, price, brand));
                    }
                    //if the ID matches of a motorcycles and in the speed range
                    else if (ID.matches(Utils.MOTO_REGEX) && (speed > Utils.MIN_SPEED && speed < Utils.MAX_SPEED)) {
                        //then add a new motorcycle to this list
                        this.add(new Motorbike(speed, isLicenseRequired, ID, name, color, price, brand));
                    }

                }

            }
            myReader.close();
            fr.close();
        } catch (Exception e) {
            if (e instanceof FileNotFoundException) {
                System.out.println("File doesn't exist.");
            }
            if (e instanceof ArrayIndexOutOfBoundsException) {
                System.out.println("Insufficient object fields. Please check the file again.");
            }
        }
        //To change body of generated methods, choose Tools | Templates.
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addVehicle() {
        //general vehicle's fields
        String ID;
        String color;
        String brand;
        String name;
        double price;
        //car's specific fields
        int yearOfManufacture;
        String type;
        //motorbike's specific fields
        boolean isLicenseRequired;
        int speed;

        int choice;

        //prompts user to choose an operation
        System.out.println("\n");
        System.out.println("1.Add a car");
        System.out.println("2.Add a motorbike");
        System.out.print("What do you wish to do (Enter 1 or 2)?");

        choice = Utils.getInt("Choice: ", 1, 2);

        if (choice == 1) {

            //If the input doesn't conform to the ID format or the ID is already exist, make the user input again
            do {
                ID = Utils.getString("Input car's ID (CXXXXX with X is a digit) ");
                if (!(ID.matches(Utils.CAR_REGEX))) {
                    System.out.println("Wrong ID format (CXXXXX with X is a digit) ");
                } else if (isIDExist(ID)) {
                    System.out.println("ID existed");
                }
            } while (isIDExist(ID) || !(ID.matches(Utils.CAR_REGEX)));
            //add car
            name = Utils.getString("Input car's name: ");
            brand = Utils.getString("Input car's brand: ");
            price = Utils.getDouble("Input car's price: ", Utils.MIN_PRICE, Utils.MAX_PRICE);
            type = Utils.getString("Input car's type: ");
            yearOfManufacture = Utils.getInt("Input your car's year of manufacture (Year >= 1886): ", Utils.MIN_YEAR);
            color = Utils.getString("Input your car's color: ");
            this.add(new Car(type, yearOfManufacture, ID, name, color, price, brand));
            System.out.println("Car added successful");

        } else if (choice == 2) {
            //add motorbike
            //If the input doesn't conform to the ID format or the ID is already exist, make the user input again
            do {
                ID = Utils.getString("Input motorbike's ID (MXXXXX with X is a digit) ");
                if (!(ID.matches(Utils.MOTO_REGEX))) {
                    System.out.println("Wrong ID format (MXXXXX with X is a digit) ");
                } else if (isIDExist(ID)) {
                    System.out.println("ID existed ");
                }
            } while (isIDExist(ID) || !(ID.matches(Utils.MOTO_REGEX)));

            name = Utils.getString("Input motorbike's name: ");
            color = Utils.getString("Input motorbike's color: ");
            brand = Utils.getString("Input motorbike's brand: ");
            price = Utils.getDouble("Input motorbike's price: ", Utils.MIN_PRICE, Utils.MAX_PRICE);
            speed = Utils.getInt("Input your vehicle's top speed: ", Utils.MIN_SPEED, Utils.MAX_SPEED);
            isLicenseRequired = Utils.getBoolean("License requirement: (true/false) ");
            this.add(new Motorbike(speed, isLicenseRequired, ID, name, color, price, brand));
            System.out.println("Motorbike added successful");

        }
        //Ask the user if they want to add another vehicle
        if (Utils.confirmYesNo("Do you want to add another vehicle? (Y/N) ")) {
            this.addVehicle();
        }
    }

    @Override
    public void updateVehicle() {
        //Vehicle's ID to update
        String uID;
        //Vehicle's fields
        String newColor;
        int newYearOfManufacture;
        boolean newIsLicenseRequired;
        int newSpeed;
        double newPrice;
        String newBrand;
        String newType;
        String newName;
        //if the list is empty then print "Empty list."
        if (this.isEmpty()) {
            System.out.println("Empty list.");
        } else {
            //Input desired vehicle's ID to update
            //If ID doesn't conform to standard format or ID doesn't exist, make the user type again
            do {
                uID = Utils.getString("Input vehicle's ID you want to update (CXXXXX or MXXXXX with X is a digit) ");
                if (!(uID.matches(Utils.VEHICLE_REGEX))) {
                    System.out.println("Wrong ID format (CXXXXX or MXXXXX with X is a digit) ");
                } else if (!isIDExist(uID)) {
                    System.out.println("Vehicle does not exist");
                }

            } while (!(uID.matches(Utils.VEHICLE_REGEX)) || (!isIDExist(uID)));
            //position of the vehicle
            int pos = pos(uID);

            //Input general vehicle's information
            newName = Utils.updateString("Input your vehicle's new name: ", this.get(pos).getName());
            newBrand = Utils.updateString("Input your vehicle's new brand: ", this.get(pos).getBrand());
            newPrice = Utils.updateDouble("Input your vehicle's new price: ", 0, this.get(pos).getPrice());
            newColor = Utils.updateString("Input your vehicle's new color: ", this.get(pos).getColor());

            //If it's a motorcycle's ID, then update the motorcycle's specific fields
            if (uID.matches(Utils.MOTO_REGEX)) {
                newSpeed = Utils.updateInt("Input your vehicle's new top speed: ", Utils.MIN_SPEED, Utils.MAX_SPEED, ((Motorbike) this.get(pos)).getSpeed());
                newIsLicenseRequired = Utils.updateBoolean("Input your vehicle's new license requirement state (true/false): ", ((Motorbike) this.get(pos)).getIsLicenseRequired());
                //Get the specific vehicle in this list, then set its field to the new information
                this.get(pos).setName(newName);
                this.get(pos).setBrand(newBrand);
                this.get(pos).setColor(newColor);
                this.get(pos).setPrice(newPrice);
                ((Motorbike) this.get(pos)).setIsLicenseRequired(newIsLicenseRequired);
                ((Motorbike) this.get(pos)).setSpeed(newSpeed);
            }
            //If it's a car's ID, then update the car's specific fields
            else if (uID.matches(Utils.CAR_REGEX)) {
                newType = Utils.updateString("Input your vehicle's new type: ", ((Car) this.get(pos)).getType());
                newYearOfManufacture = Utils.updateInt("Input your vehicle's new year of manufacture (Year >= 1886): ", Utils.MIN_YEAR, ((Car) this.get(pos)).getYearOfManufacture());
                //Get the specific vehicle in this list, then set its field to the new information
                this.get(pos).setName(newName);
                this.get(pos).setBrand(newBrand);
                this.get(pos).setColor(newColor);
                this.get(pos).setPrice(newPrice);
                ((Car) this.get(pos)).setType(newType);
                ((Car) this.get(pos)).setYearOfManufacture(newYearOfManufacture);

            }

            //Notify the user that the update process is success and print out the result
            System.out.println("Vehicle updated successful. This is the result: ");
            System.out.println(this.get(pos).toString());
            //Ask user if they want to update another vehicle
            if (Utils.confirmYesNo("Do you want to update another vehicle? (Y/N) ")) {
                this.updateVehicle();
            }
        }
    }

    @Override
    public void deleteVehicle() {
        //Vehicle's ID to remove
        String rID;

        //Notify the user if the list is empty
        if (this.isEmpty()) {
            System.out.println("Empty list");
        } else {
            //If the ID doesn't conform to the format or doesn't exist, make the user type again
            do {
                rID = Utils.getString("Input vehicle's ID you want to remove (CXXXXX or MXXXXX with X is a digit) ");
                if (!(rID.matches(Utils.VEHICLE_REGEX))) {
                    System.out.println("Wrong ID format (CXXXXX or MXXXXX with X is a digit) ");
                } else if (!isIDExist(rID)) {
                    System.out.println("Vehicle does not exist");
                }

            } while (!(rID.matches(Utils.VEHICLE_REGEX)) || (!isIDExist(rID)));

            //Position of the vehicle to be removed
            int pos = pos(rID);

            //Warns the user before they remove a vehicle
            String warning = Utils.getString("Are you sure that you want to remove " + this.get(pos).getName() + "? (Y/N) ");
            //If the user type "Y" then remove the vehicle, else do nothing and notify that the removal is canceled
            if (warning.equals("Y")) {
                this.remove(pos);
                System.out.println(rID + " is removed");
            } else {
                System.out.println("Remove canceled");
            }
        }
        //Asks if the user wants to remove another vehicle
        if (Utils.confirmYesNo("Do you want to delete another vehicle? (Y/N) ")) {
            this.deleteVehicle();
        }
    }

    @Override
    public void searchVehicle() {

        int choice;
        String ID;
        String name;
        int count = 0;

        //prompts user to either search by name or by id
        System.out.println("\n");
        System.out.println("1.Search by name");
        System.out.println("2.Search by ID");
        System.out.print("What do you wish to do (Enter 1 or 2)? ");

        choice = Utils.getInt("Choice: ", 1, 2);
        if (choice == 1) {
            if (this.isEmpty()) {
                System.out.println("Empty list");
            } else {
                String keyword;
                keyword = Utils.getString("Input your vehicle name: ");
                //. matches any character (except for line terminators)
                //* matches the previous token between zero and unlimited times, as many times as possible, giving back as needed (greedy)
                //() matches any position
                name = ".*(" + keyword + ").*";

                //For each vehicle in this list
                for (Vehicle x : this) {
                    //If the vehicle's name matches the input name
                    if (x.getName().matches(name)) {
                        //If the vehicle is a car
                        if (x instanceof Car) {
                            //Print car's information
                            System.out.println("Car info: " + "ID: " + x.getID() + "\nName: " + x.getName() + "\nBrand: " + x.getBrand() + "\nColor: " + x.getColor() + "\nPrice: " + x.getPrice() + "\nType: " + ((Car) x).getType() + "\nYear of manufacture: " + ((Car) x).getYearOfManufacture());
                        }
                        //If the vehicle is a motorcycle
                        else if (x instanceof Motorbike) {
                            //Print out motorcycle's information and invoke makeSound()
                            System.out.println("Motorcycle info: " + "ID: " + x.getID() + "\nName: " + x.getName() + "\nBrand: " + x.getBrand() + "\nColor: " + x.getColor() + "\nPrice: " + x.getPrice() + "\nSpeed: " + ((Motorbike) x).getSpeed() + "\nLicense required: " + ((Motorbike) x).getIsLicenseRequired());
                            ((Motorbike) x).makeSound();
                        }
                        //Increase count when a vehicle matches input name
                        count++;

                    }


                }

            }
        }//search by ID
        else if (choice == 2) {
            if (this.isEmpty()) {
                System.out.println("Empty list");
            } else {
                do {
                    ID = Utils.getString("Input vehicle's ID (CXXXXX or MXXXXX with X is a digit) ");
                    if (!(ID.matches(Utils.VEHICLE_REGEX))) {
                        System.out.println("Wrong ID format (CXXXXX or MXXXXX with X is a digit) ");
                    }
                } while (!(ID.matches(Utils.VEHICLE_REGEX)));

                String reg = ID;
                //sort descending by price
                Collections.sort(this, new Comparator<Vehicle>() {


                    @Override
                    public int compare(Vehicle v1, Vehicle v2) {
                        if (v1.getPrice() < v2.getPrice()) {
                            return 1;
                        } else if (v1.getPrice() > v2.getPrice()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }


                });
                //For each vehicle in this list
                for (Vehicle x : this) {
                    //If the vehicle's ID matches the input ID
                    if (x.getID().matches(reg)) {
                        //If it's a car
                        if (x instanceof Car) {
                            //then print out car's information
                            System.out.println("Car info: " + "ID: " + x.getID() + "\nName: " + x.getName() + "\nBrand: " + x.getBrand() + "\nColor: " + x.getColor() + "\nPrice: " + x.getPrice() + "\nType: " + ((Car) x).getType() + "\nYear of manufacture: " + ((Car) x).getYearOfManufacture());
                        }
                        //If it's a motorcycle
                        else if (x instanceof Motorbike) {
                            //then print out motorcycle's information
                            System.out.println("Motorcycle info: " + "ID: " + x.getID() + "\nName: " + x.getName() + "\nBrand: " + x.getBrand() + "\nColor: " + x.getColor() + "\nPrice: " + x.getPrice() + "\nSpeed: " + ((Motorbike) x).getSpeed() + "\nLicense required: " + ((Motorbike) x).getIsLicenseRequired());
                        }
                        //Increase count when a vehicle matches input ID
                        count++;

                    }

                }

            }//search by id

        }
        //If count is less than one it means there's no vehicle match
        if (count < 1) {
            System.out.println("Vehicle doesn't exist");
        }
        System.out.println("\n");
        //Asks the user if they want to search another vehicle
        if (Utils.confirmYesNo("Do you want to search another vehicle? (Y/N) ")) {
            this.searchVehicle();
        }
    }

    @Override
    public void showVehicleList() {
        int choice;

        System.out.println();
        //Prompts the user if they want to show all or show all descended by price
        System.out.println("1.Show all");
        System.out.println("2.Show all (descending by price)");
        choice = Utils.getInt("Input your choice: ", 1, 2);
        System.out.println("\n");
        //Notify user if the list is empty
        if (this.isEmpty()) {
            System.out.println("Empty list");
        } else {
            if (choice == 1) {
                //show all
                //For each vehicle in this list
                System.out.println("VEHICLE INFORMATION");
                System.out.println("----------------------------");

                for (Vehicle x : this) {
                    //If it's a car
                    if (x instanceof Car) {
                        //then show all the car's information
                        System.out.println(String.format("%-7s|%-7s|%-7s|%-7s|%-7s|%-7s|%-19s|", "ID", "Name", "Brand", "Color", "Price", "Type", "Year of manufacture"));
                        System.out.println(String.format("%-7s|%-7s|%-7s|%-7s|%-7.1f|%-7s|%-19d|", x.getID(), x.getName(), x.getBrand(), x.getColor(), x.getPrice(), ((Car) x).getType(), ((Car) x).getYearOfManufacture()));

                    }
                    //If it's a motorcycle
                    else if (x instanceof Motorbike) {
                        //then show all the motorcycle's information
                        System.out.println(String.format("%-7s|%-7s|%-7s|%-7s|%-7s|%-7s|%-19s|", "ID", "Name", "Brand", "Color", "Price", "Speed", "License requirement"));
                        System.out.println(String.format("%-7s|%-7s|%-7s|%-7s|%-7.1f|%-7d|%-19b|", x.getID(), x.getName(), x.getBrand(), x.getColor(), x.getPrice(), ((Motorbike) x).getSpeed(), ((Motorbike) x).getIsLicenseRequired()));
                    }
                    System.out.println("\n");
                }
            } else if (choice == 2) {
                //show all (descending by price) and invoke makeSound()(
                //Sort descending by price
                Collections.sort(this, new Comparator<Vehicle>() {
                    @Override
                    public int compare(Vehicle v1, Vehicle v2) {
                        if (v1.getPrice() < v2.getPrice()) {
                            return 1;
                        } else if (v1.getPrice() > v2.getPrice()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                });
                //For each vehicle in this list
                for (Vehicle x : this) {
                    //If it's a car
                    if (x instanceof Car) {
                        //then prints out car's information
                        System.out.println(String.format("%-7s|%-7s|%-7s|%-7s|%-7s|%-7s|%-19s|", "ID", "Name", "Brand", "Color", "Price", "Type", "Year of manufacture"));
                        System.out.println(String.format("%-7s|%-7s|%-7s|%-7s|%-7.1f|%-7s|%-19d|", x.getID(), x.getName(), x.getBrand(), x.getColor(), x.getPrice(), ((Car) x).getType(), ((Car) x).getYearOfManufacture()));
                    }
                    //If it's a motorcycle
                    else if (x instanceof Motorbike) {
                        //then prints out motorcycle's information and invoke makeSound()
                        System.out.println(String.format("%-7s|%-7s|%-7s|%-7s|%-7s|%-7s|%-19s|", "ID", "Name", "Brand", "Color", "Price", "Speed", "License requirement"));
                        System.out.println(String.format("%-7s|%-7s|%-7s|%-7s|%-7.1f|%-7d|%-19b|", x.getID(), x.getName(), x.getBrand(), x.getColor(), x.getPrice(), ((Motorbike) x).getSpeed(), ((Motorbike) x).getIsLicenseRequired()));
                        ((Motorbike) x).makeSound();
                    }

                    System.out.println("\n");
                }
            }

        }
    }

    @Override
    public void saveToFile() {
        //If the list is empty then notify the user that it's empty
        if (this.isEmpty()) {
            System.out.println("\n");
            System.out.println("Empty list");
        }
        //Else save the vehicle list to the text file
        else {
            System.out.println("\n");
            //Prompts the user to enter the file name to save to
            System.out.print("Type in the file's name (File's extension already included): ");
            Scanner sc = new Scanner(System.in);
            //Input the file name
            String filename = sc.nextLine().trim();
            filename = filename + ".txt";
            try {
                //Create new file object with the file name argument
                File f = new File(filename);

                try (OutputStreamWriter writer
                             = new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8)) {
                    //For each vehicle in this list
                    for (Vehicle e : this) {
                        //write them into the file
                        writer.write(e.toString());
                        writer.write("\n");
                    }
                    //Notify that the save is success
                    System.out.println("Save successful");

                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    private int pos(String ID) {
        //If this list is not empty then return the vehicle's position in the list
        if (!this.isEmpty()) {
            //Loop through every index
            for (int i = 0; i < this.size(); i++) {
                //If the ID of the vehicle in the index matches the input ID
                if (this.get(i).getID().equals(ID)) {
                    //then return the vehicle's position
                    return i;
                }
            }
        }
        //If nothing is found then return the negative index
        return -1;
    }

    private boolean isIDExist(String ID) {
        //return the boolean value of whether the vehicle with the input ID is null or not
        return search(ID) != null;
    }

    private Vehicle search(String ID) {
        //For each vehicle in this list
        for (Vehicle x : this) {
            //If the ID of the vehicle matches the input ID
            if (x.getID().equals(ID)) {
                //then return the vehicle
                return x;
            }
        }
        //If nothing is found then return null
        return null;
    }

}
