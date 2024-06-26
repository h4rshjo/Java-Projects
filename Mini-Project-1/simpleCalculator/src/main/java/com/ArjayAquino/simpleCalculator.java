package com.ArjayAquino;

import java.util.InputMismatchException;
import java.util.Scanner;

public class simpleCalculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        boolean validChoice = false;
        while (!validChoice) {
            clearScreen();
            System.out.println("-----------------");
            System.out.println("Simple Calculator");
            System.out.println("-----------------");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.print("\n" + "Enter your choice: ");

            try {
                choice = sc.nextInt(); // Get user input for operation

                if (choice >= 1 && choice <= 4) {
                    validChoice = true;
                } else {
                    System.out.println("Error! Please enter a number between 1 and 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error! Please enter a numeric value.");
                sc.next(); // clear the invalid input
            }
        }

        double num1 = 0, num2 = 0;
        boolean validNumbers = false;

        while (!validNumbers) {
            try {
                System.out.print("Enter the first number: ");
                num1 = sc.nextDouble();
                System.out.print("Enter the second number: ");
                num2 = sc.nextDouble();
                validNumbers = true;
            } catch (InputMismatchException e) {
                System.out.println("Error! Please enter numeric values.");
                sc.next(); // clear the invalid input
            }
        }

        // Switch case statements for methods
        switch (choice) {
            case 1:
                System.out.println(num1 + " + " + num2 + " = " + Addition(num1, num2));
                break;
            case 2:
                System.out.println(num1 + " - " + num2 + " = " + Subtraction(num1, num2));
                break;
            case 3:
                System.out.println(num1 + " * " + num2 + " = " + Multiplication(num1, num2));
                break;
            case 4:
                double result = Division(num1, num2);
                if (!Double.isNaN(result)) {
                    System.out.println(num1 + " / " + num2 + " = " + result);
                }
                break;
        }

        sc.close();
    }

    public static double Addition(double num1, double num2) {
        return num1 + num2;
    }

    public static double Subtraction(double num1, double num2) {
        return num1 - num2;
    }

    public static double Multiplication(double num1, double num2) {
        return num1 * num2;
    }

    public static double Division(double num1, double num2) {
        if (num2 == 0) {
            System.out.println("Error: Division by zero!");
            return Double.NaN;
        } else {
            return num1 / num2;
        }
    }
    public static void clearScreen(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
}