package org.example;

import java.util.Scanner;

public class simpleCalculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------");
        System.out.println("Simple Calculator");
        System.out.println("-----------------");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        System.out.print("\n" + "Enter your choice: ");

        int choice = sc.nextInt(); // Get user input for operation

        double num1, num2;

        System.out.println("Enter the first number: ");
        num1 = sc.nextDouble();
        System.out.println("Enter the second number: ");
        num2 = sc.nextDouble();

        //Switch case statement for methods that will be used
        switch (choice) {
            case 1:
                performAddition(num1, num2);
                break;
            case 2:
                performSubtraction(num1, num2);
                break;
            case 3:
                performMultiplication(num1, num2);
                break;
            case 4:
                performDivision(num1, num2);
                break;
            default:
                System.out.println("Invalid choice!");
        }

        sc.close();
    }

    public static void performAddition(double num1, double num2) {
        double result = num1 + num2;
        System.out.println(num1 + " + " + num2 + " = " + result);
    }

    public static void performSubtraction(double num1, double num2) {
        // Implement subtraction logic here
        double result = num1 - num2;
        System.out.println(num1 + " - " + num2 + " = " + result);
    }

    public static void performMultiplication(double num1, double num2) {
        // Implement multiplication logic here
        double result = num1 * num2;
        System.out.println(num1 + " * " + num2 + " = " + result);
    }

    public static void performDivision(double num1, double num2) {
        // Implement division logic here with division by zero check
        if (num2 == 0) {
            System.out.println("Error: Division by zero!");
        } else {
            double result = num1 / num2;
            System.out.println(num1 + " / " + num2 + " = " + result);
        }
    }
}