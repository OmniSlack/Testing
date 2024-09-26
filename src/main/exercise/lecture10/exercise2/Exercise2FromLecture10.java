package com.homework.exercise.lecture10.exercise2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
File Reading and Parsing - You need to write a Java program that reads an
integer from a file and parses it to calculate the square root. If the file
cannot be found (FileNotFoundException) or if the content of the file is not
a valid integer(NumberFormatException), handle these exceptions
appropriately and print messages to the user. Hint
 */


public class Exercise2FromLecture10 {

    public static void main(String[] args) {
        String filePatch = "F:\\Repos\\my_first_repo\\src\\com\\homework\\exercise\\lecture10\\number.txt";

        try {
            File file = new File(filePatch);
            Scanner scanner = new Scanner(file);

            String line = scanner.nextLine();
            int number = Integer.parseInt(line);

            double squareRoot = Math.sqrt(number);
            System.out.println("The square root of" + number + " is " + squareRoot);

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error: The file was not found");
        } catch (NumberFormatException e) {
            System.out.println("Error: The file does not contain a valid integer");

        }
    }
}


