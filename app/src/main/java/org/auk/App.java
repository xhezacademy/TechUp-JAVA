package org.auk;

import org.auk.data.StudentRepository;
import org.auk.models.Student;
import org.auk.utils.Faker;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    private static int X_TIMES = 65;

    public static void main( String[] args )
    {
        //StudentRepository repository = new StudentRepository();

        /*// Table Headers
        printTableBanner();
        printTableColumns();

        for (Student student : repository.getAlAl()) {
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

        *//*
          Get Student by id
         *//*
        Student theStudent = repository.getById(2);
//        Objects.requireNonNull(theStudent);

        *//*
          Add student to the list
         *//*
        Student s = new Student();
        s.setId(11);
        s.setFirstName("Xhevat");
        s.setLastName("Xhezairi");
        s.setGender(Student.Gender.MALE);
//        repository.add(s);*/

        Scanner scan = new Scanner(System.in) ;
        String response = "" ;
        int id = 0 ;

        Faker f = new Faker("C:\\Users\\Blerton Shabani\\Desktop\\TechUp-JAVA\\app\\src\\main\\java\\org\\auk\\db\\database.txt") ;

        do {

            String line = f.findLastLine() ;
            String [] split = line.split(" ") ;

            if (!line.trim().isEmpty()) {
                id = Integer.parseInt(split[0]);
            }

            System.out.print("First Name : ");
            String firstName = scan.nextLine();

            System.out.print("Last Name : ");
            String lastName = scan.nextLine();

            System.out.print("Gender : ");
            String gender = scan.nextLine();

            System.out.print("Phone number : +383");
            String phone = scan.nextLine() ;

            Student s = new Student();
            s.setId(++id);
            s.setFirstName(firstName);
            s.setLastName(lastName);
            s.setGender(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("male") ? 1 : 2);
            s.setPhone(phone);
/*
            repository.add(s);
*/
            f.writeStudentsInFiles(s);

            System.out.println("Do you want to add another student ? \nPress \"Y\" for yes or \"N\" for no : ");
            response = scan.nextLine();



        } while (response.equalsIgnoreCase("y")) ;



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
