/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * This is comment, do not delete 2021.11.30
 * and open the template in the editor.
 */
package lab211.assignment;


import java.util.Scanner;

/**
 * @author hd
 */
public class Utils {

    public static final String VEHICLE_REGEX = "[CM]\\d{5}"; //(CXXXXX or MXXXXX with X is a digit)
    public static final String CAR_REGEX = "[C]\\d{5}";//(CXXXXX with X is a digit)
    public static final String MOTO_REGEX = "[M]\\d{5}";//(MXXXXX with X is a digit)
    public static final int MAX_SPEED = 400;
    public static final int MIN_SPEED = 0;
    public static final double MIN_PRICE = 0;
    public static final double MAX_PRICE = Double.MAX_VALUE;

    public static final int MIN_YEAR = 1886;
    public static final int MAX_YEAR = Integer.MAX_VALUE;


    public static String getString(String welcome) {
        //default return true
        boolean check = true;
        //default string is empty
        String result = "";
        do {
            //Prompts user to input string
            Scanner sc = new Scanner(System.in);
            System.out.print(welcome);
            result = sc.nextLine();
            //If result is empty then print the following message
            if (result.isEmpty()) {
                System.out.println("Input text!!!");
            }
            //Else exit loop with changing check value to false
            else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static String inputPStr(String msg, String pat) {
        //Default string is empty
        String data;
        Scanner sc = new Scanner(System.in);
        //If the data doesn't match the pattern, then make the user input the string again
        do {
            System.out.print(msg);
            //trim to remove all trailing characters
            data = sc.nextLine().trim();
        } while (!data.matches(pat));
        return data;
    }

    public static String updateString(String welcome, String oldData) {
        //Default result is the old data so that when the user doesn't input, it will not change the data
        String result = oldData;
        Scanner sc = new Scanner(System.in);
        System.out.print(welcome);
        String tmp = sc.nextLine();
        //If the tmp variable is not null then set the result to the new input
        if (!tmp.isEmpty()) {
            result = tmp;
        }
        return result;
    }

    public static int getInt(String welcome, int min, int max) {
        boolean check = true;
        int number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }

    public static int getInt(String welcome, int min) {
        boolean check = true;
        int number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number < min);
        return number;
    }

    public static int updateInt(String welcome, int min, int max, int oldData) {
        boolean check = true;
        int number = oldData;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    number = Integer.parseInt(tmp);
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }

    public static int updateInt(String welcome, int min, int oldData) {
        boolean check = true;
        int number = oldData;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    number = Integer.parseInt(tmp);
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number < min);
        return number;
    }

    public static boolean confirmYesNo(String welcome) {
        //default result is false
        boolean result = false;
        //get string
        String confirm = Utils.getString(welcome);
        //only accepts [Nn] or [Yy], otherwise put user in a loop
        while (!"N".equalsIgnoreCase(confirm) && !"Y".equalsIgnoreCase(confirm)) {
            System.out.print("Only Y or N is allowed. Please input again: ");
            confirm = Utils.getString(welcome);
        }
        if ("Y".equalsIgnoreCase(confirm)) {
            result = true;
        } else if ("N".equalsIgnoreCase(confirm)) {
            result = false;
        }
        return result;
    }

    public static double getDouble(String welcome, double min, double max) {
        double number = 0;
        boolean check = true;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                number = Double.parseDouble(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number < min || number > max);

        return number;
    }

    public static double updateDouble(String welcome, double min, double oldData) {
        boolean check = true;
        double number = oldData;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    number = Integer.parseInt(tmp);
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number < min);
        return number;
    }

    public static boolean getBoolean(String welcome) {

        boolean check = true;
        boolean result = false;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println(welcome);
                result = sc.nextBoolean();
                check = false;
            } catch (Exception e) {
                System.out.println("Only input true or false");
            }
        } while (check);

        return result;
    }

    public static boolean updateBoolean(String welcome, boolean oldData) {
        boolean check = true;
        boolean result = oldData;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                String tmp = sc.nextLine();

                result = sc.nextBoolean();
                check = false;

            } catch (Exception e) {
                System.out.println("Input boolean!!!");
            }
        } while (check);
        return result;
    }


}
