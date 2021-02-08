package ui;

import model.Grade;
import model.GradesCalculator;

import java.util.List;
import java.util.Scanner;

// !!! no comments yet

public class GradesCalculatorConsoleInterface {

    private GradesCalculator gradesCalculator;
    private Scanner input;

    public GradesCalculatorConsoleInterface() {
        runConsoleInterface();
    }

    private void runConsoleInterface() {
        boolean continueInterface = true;
        String operation;

        initialize();

        while (continueInterface) {
            printMenu();
            operation = input.nextLine().toLowerCase();
            if (operation.equals("q")) {
                continueInterface = false;
            } else {
                processOperation(operation);
            }
        }
        System.out.println("Thank you, see you next time!");
    }

    private void initialize() {
        gradesCalculator = new GradesCalculator();
        input = new Scanner(System.in);
    }

    private void printMenu() {
        System.out.println("Please choose one of the following: \n"
                + "a  - Add a new grade \n"
                + "r  - remove grade \n"
                + "va - (view all) View all grades \n"
                + "vc - (view class) View grades for a specific class \n"
                + "c  - View the average for a specific class \n"
                + "co - (calculate overall) Calculate overall average \n"
                + "q  - Quit program");
    }

    private void processOperation(String operation) {
        switch (operation) {
            case ("a"):
                addRemoveGrade(0);
                break;
            case ("r"):
                addRemoveGrade(1);
                break;
            case ("va"):
                viewAll();
                break;
            case ("vc"):
                viewClass();
                break;
            case ("c"):
                oneAverage();
                break;
            case ("co"):
                overallAverage();
                break;
        }
    }

    private void addRemoveGrade(int command) {
        Grade grade;
        double mark;
        String assignment;
        String className;

        System.out.println("Please enter a grade.");
        mark = Double.parseDouble(input.nextLine());
        System.out.println("Please enter the assignment name.");
        assignment = input.nextLine();
        System.out.println("Please enter the class name.");
        className = input.nextLine();

        grade = new Grade(mark, assignment, className);

        if (command == 0) {
            gradesCalculator.addGrade(grade);
        } else if (command == 1) {
            gradesCalculator.removeGrade(grade);
        }

    }

    private void viewAll() {
        List<Grade> grades = gradesCalculator.getGrades();

        for (Grade grade : grades) {
            System.out.println(grade);
        }

    }

    private void viewClass() {
        String command;
        System.out.println("Please insert a class.");
        command = input.nextLine();
        List<Grade> output = gradesCalculator.returnClassGrades(command);

        for (Grade grade : output) {
            System.out.println(grade);
        }

    }

    private void oneAverage() {
        String command;
        System.out.println("Please insert a class.");
        command = input.nextLine();
        double output = gradesCalculator.calculateClassAverage(command);

        System.out.println(output);
    }

    private void overallAverage() {
        System.out.println(gradesCalculator.calculateOverallAverage());
    }

}
