package my.project;

import java.util.Scanner;

public class InvalidFileNameException extends Exception {
Scanner sc = new Scanner(System.in);
    public InvalidFileNameException (String output){
        super(output);
    }
    }
