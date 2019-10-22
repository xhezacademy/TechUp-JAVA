package org.auk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.auk.data.StudentRepository;
import org.auk.models.Student;
import org.auk.utils.ConsoleColors;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Scanner;

/**
 * TechUp JAVA 2019
 *
 * Extra JavaFX GUI components http://www.jfoenix.com
 */
public class App extends Application
{
    private final static String DB_URL = "jdbc:mariadb://localhost/techupdb?useSSL=false";

    private static int X_TIMES = 65;

    private static StudentRepository repository;

    public static void main( String[] args )
    {
        //launch(args);
        //launchCLI(args);

//            Properties dbProperties = new Properties();
//            dbProperties.put("username", "root");
//            dbProperties.put("password", "root");

        try (var connection = DriverManager.getConnection(DB_URL, "root", "root");
             var stmt = connection.createStatement();
             var results = stmt.executeQuery("SELECT * FROM students")) {

            // Load Database Driver
            Class.forName("org.mariadb.jdbc.Driver");
//            DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
//            print(connection.getClass().getSimpleName());
//            print(stmt.getClass().getSimpleName());

            // Print returned results
//            var results = stmt.executeQuery("SELECT 'Hello World'");;
            while (results.next()) {
                print("ID: "+ results.getString(1)
                    + "\nName: " + results.getString("firstName"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/layout_main.fxml"));
        Scene scene = new Scene(root, 300, 275);
        primaryStage.setTitle("Hello TechUp!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void launchCLI(String... args) {
        print(ConsoleColors.PURPLE_BACKGROUND_BRIGHT + ConsoleColors.BLACK_BOLD + " ~ Welcome to Student Management System 2020 ~ " + ConsoleColors.RESET);
        print(ConsoleColors.YELLOW_BACKGROUND_BRIGHT + ConsoleColors.BLUE_UNDERLINED + "Please fill in the data for the new student..." + ConsoleColors.RESET);
        print("=".repeat(50));

        repository = new StudentRepository(5);

        boolean receiving = true;

        for (;receiving;) {
            String studentData = readNewStudentDataFromInput();
            repository.add(repository.getNextRecordId() + ", " + studentData, true);
            repository.refreshList();

            print("Do you want to create another student record? Y/n:");
            Scanner sc = new Scanner(System.in);
            char answer = sc.next().charAt(0);

            switch (answer) {
                case 'N', 'n' -> receiving = false;
            }
        }

        print(ConsoleColors.RED_BOLD + "Thank you for your service, and see you soon again! ^_^" + ConsoleColors.RESET);

        // Table Headers
        printTableBanner();
        printTableColumns();
        printTableBody();
    }

    private static String readNewStudentDataFromInput() {
        String[] columns = {"Name", "Surname", "Birthday", "Phone", "Email"};

        // Create a Scanner instance and initialize with
        // predefined standard input object
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        // Initialize count of input elements
        int count = 0;

        // Check if an int value is available
        while (count < columns.length)
        {
            print(ConsoleColors.BLUE_BACKGROUND_BRIGHT + ConsoleColors.WHITE_BOLD_BRIGHT + columns[count] + ":" + ConsoleColors.RESET);
            sb.append(sc.nextLine()).append(", ");
            count++;
        }

//        String[] newData = sb.toString().split(",");
//        Student newStudent = new Student();
//        newStudent.setFirstName(newData[0]);
//        newStudent.setLastName(newData[1]);
//        newStudent.setEmail(newData[3]);
//        newStudent.setPhone(newData[4]);

//        try {
//            Date dob = new SimpleDateFormat("dd/MM/yyyy").parse(newData[2]);
//            newStudent.setDob(dob);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        return sb.toString();
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

    /**
     * Prints a message
     *
     * @param message Message to print
     */
    private static void print(String message) {
        System.out.println(message);
    }
}
