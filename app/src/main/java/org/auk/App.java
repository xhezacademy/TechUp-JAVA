package org.auk;

import org.auk.data.StudentRepository;
import org.auk.models.Student;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * TechUp JAVA - SAMS
 */
public class App 
{
    private static int X_TIMES = 65;

    private final static String DB_FILE_NAME = "database.txt";
    private final static String dbFileName = System.getProperty("user.dir") + File.separator + "db" + File.separator + DB_FILE_NAME;


    private static StudentRepository repository;

    public static void main( String[] args )
    {
        print("Welcome to Student Management System 2020");

        repository = new StudentRepository(5);

        // Table Headers
        printTableBanner();
        printTableColumns();
        printTableBody();
        sendToFile();
    }

    private static void printTableBody() {
        for (Student student : repository.getAll()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d MMM, Y", Locale.getDefault());
            String output = "| {0} " + " ".repeat(7 - (String.valueOf(student.getId()).length()))
                    + "| {1} " + " ".repeat(13 - (student.getFirstName().length() - 1))
                    + "| {2}" + " ".repeat(16 - (simpleDateFormat.format(student.getDob()).length() - 1))
                    + "| {3}" + " ".repeat(15 - (student.getPhone().length() - 1))
                    + "|";
            MessageFormat mf = new MessageFormat(output);
            print(mf.format(new Object[] {student.getId(), student.getFirstName(),
                    simpleDateFormat.format(student.getDob()), student.getPhone()}));
            print("-".repeat(X_TIMES));
        }

        /*
          Get Student by id
         */
        Student theStudent = repository.getById(2);
//        Objects.requireNonNull(theStudent);

        /*
          Add student to the list
         */
        Student s = new Student();
        s.setId(11);
        s.setFirstName("Xhevat");
        s.setLastName("Xhezairi");
        s.setGender(Student.Gender.MALE);
//        repository.add(s);
    }

    private static void printTableColumns() {
        print("| ID " + " ".repeat(5) + "| NAME " + " ".repeat(10) + "| DoB " + " ".repeat(13) + "| TEL " + " ".repeat(12) + "|");
        print("-".repeat(X_TIMES));
    }

    private static void printTableBanner() {
        String banner =
                "=".repeat(X_TIMES) + System.getProperty("line.separator") +
                "|                       Hello JAVA Gurus!                       |\n" +
                "=".repeat(X_TIMES);
        print(banner);
    }

    private static void sendToFile() {
        Scanner reader = new Scanner(System.in);

        String[] columns = new String[]{"Name", "Surname", "Birthday", "Phone"};
        StringBuilder sb = new StringBuilder();
        boolean check = true;
        String result = "";

        while (check) {
            for(int i = 0; i < 4; i++) {
                System.out.println("Enter " + columns[i] + ": ");
                sb.append(reader.nextLine() + ", ");
            }

            result = (checkID() + 1) + ", " + sb;

            try {
                Files.write(Path.of(dbFileName), (result + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Enter another record or exit? 'enter'/'exit'");
            String secondInput = reader.nextLine();
            if (secondInput.equals("exit")){
                check = false;
            }else if(secondInput.equals("enter")){
                result = "";
                check = true;
            }
        }
    }

    private static int checkID(){
        int lastID = -1;
        try {
            List<String> lines = Files.readAllLines(Path.of(dbFileName));

            for (var line : lines){
                String[] values = line.split("\\s*,\\s*");
                lastID = Integer.parseInt(values[0]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastID;
    }

    /**
     * Prints a message
     *
     * @param message Message to print
     */
    private static void print(String message) {
        System.out.println(message);
    }
}
