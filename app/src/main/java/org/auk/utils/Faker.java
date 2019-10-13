package org.auk.utils;

import org.auk.models.Student;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Faker {

    private final static String DB_FILE_NAME = "database.txt";

    public static List<Student> buildMockStudentsFromCollection(int studentCount) {
        List<Student> students = new ArrayList<>(studentCount);

        String[] femaleNames = {
                "Delana", "Nichole", "Maybelle", "Vernia", "Shanna", "Aura", "Charlena", "Vergie", "Martina", "Jeanne"
        };

        String[] maleNames = {
                "Tracy", "Allen", "Rogelio", "Kurtis", "Emmitt", "Leroy", "Carl", "Valentine", "Silas", "Darius"
        };

        String[] surnames = {
                "Sabin", "Toms", "Sautner", "Tworek", "Brooker", "Viator", "Lipford", "Camden", "Juhl", "Schieber", "Spies", "Glavin",
                "Twomey", "Scanlon", "Herbst", "Brandstetter", "Harnois", "Dehoyos", "Northrup", "Putman", "Casterline", "Bettes", "Raley",
                "Dame", "Gowin", "Glaude", "Criss", "Mumm", "Braddy", "Olin",
        };

        int[] genders = {Student.Gender.MALE, Student.Gender.FEMALE};

        for (int i = 0; i < studentCount; i++) {
            Student student = new Student();
            Random random = new Random();

            student.setId(i + 1);
            student.setGender(genders[random.nextInt(genders.length)]);

            if (student.getGender() == Student.Gender.FEMALE) {
                student.setFirstName(femaleNames[random.nextInt(femaleNames.length)]);
            } else {
                student.setFirstName(maleNames[random.nextInt(maleNames.length)]);
            }

            student.setLastName(surnames[random.nextInt(surnames.length)]);
            student.setPhone("0" + (100000000 + random.nextInt(899999999)));

            Calendar dateOfBirth = Calendar.getInstance();
            dateOfBirth.add(Calendar.DAY_OF_MONTH, -((18 * 365) + random.nextInt(60 * 365)));
            System.out.println(dateOfBirth.getTime());
            student.setDob(dateOfBirth.getTime());

            students.add(student);
        }

        return students;
    }

    /**
     * Reads input stream from an existing text file
     * <<<<<<< HEAD
     * TODO: implement FileSystem DataSource
     *
     * @param ======= >>>>>>> refs/remotes/origin/master
     */
    public static List<Student> buildMockStudentsFromFile() {
        List<Student> studentList = new ArrayList<>();

        final String dbFileName = System.getProperty("user.dir") + File.separator + "db" + File.separator + DB_FILE_NAME;
        final File file = new File(dbFileName);

        try (final FileInputStream fileInputStream = new FileInputStream(file)) {
            if (!file.exists()) {
                file.createNewFile();
            }

            // Read char decimal value
//            int i;
//            while ((i = fileInputStream.read()) != -1) {
//                System.out.println((char) i);
//            }

            // Read by Buffer length
//            byte[] buffer = new byte[1024];
//            int i = fileInputStream.read(buffer);
//
//            while (i != -1) {
//                String value = new String(buffer, StandardCharsets.UTF_8);
//                System.out.println(value);
//                i = fileInputStream.read(buffer);
//            }

            // Read line by line
//            BufferedReader reader = new BufferedReader(new FileReader(dbFileName, StandardCharsets.UTF_8));
//            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream, StandardCharsets.UTF_8));

//            var sb = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                sb.append(line);
//            }
//            System.out.println(sb.toString());

            // JAVA 8 File API
//            String content = Files.readString(Path.of(dbFileName));
            List<String> lines = Files.readAllLines(Path.of(dbFileName));

            for (var line : lines) {
                String[] values = line.split("\\s*,\\s*");

                Student student = new Student();
                student.setId(Integer.parseInt(values[0]));
                student.setFirstName(values[1].trim());
                student.setLastName(values[2].trim());
                student.setPhone(values[4]);

                SimpleDateFormat simpleDateFormat =
                        new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                Date dob = simpleDateFormat.parse(values[3]);
                student.setDob(dob);

                studentList.add(student);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
//        finally {
//            if (fileInputStream != null) {
//                fileInputStream.close();
//            }
//        }

        return studentList;
    }

    public static List<Student> buildMockStudentsFromScanner() {


        Scanner sc = new Scanner(System.in);
        List<Student> studentList = new ArrayList<>();
        String end = "end";
        System.out.println();
        String name = "";
        String lastName = "";
        String birthday = "";
        String phone = "";
        int input = 0;
        boolean terminate = false;
        int id = 0;
        while (terminate == false) {
            System.out.println();
            System.out.println("============== Regjistro Student ==============");
            System.out.print("Name : ");
            name = sc.nextLine();
            System.out.print("LastName : ");
            lastName = sc.nextLine();
            System.out.print("Birthday : ");
            birthday = sc.nextLine();
            System.out.print("Phone : ");
            phone = sc.nextLine();

            Student student = new Student();
            student.setFirstName(name);
            student.setLastName(lastName);
            SimpleDateFormat simpleDateFormat =
                    new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            Date dob = null;
            try {
                dob = simpleDateFormat.parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int lastId = getLastId();
            student.setId(lastId+1);
            student.setDob(dob);
            student.setPhone(phone);
            studentList.add(student);
            System.out.println();

            System.out.print("Type : 1 for (New Student) , 2 for (Terminate) : ");

            input = sc.nextInt();

            while (input != 1 && input != 2) {
                System.out.print("Type : 1 for (New Student) , 2 for (Terminate) : ");
                input = sc.nextInt();
            }

            terminate = input == 2 ? true : false;
            sc.nextLine();

        }
        final String dbFileName = System.getProperty("user.dir") + File.separator + "db" + File.separator + DB_FILE_NAME;
        final File file = new File(dbFileName);
        if(!file.exists()){
            System.out.println("File not exist");
        }

        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
            if(printWriter != null){
                System.out.println("========exists");
            }

            for (Student s : studentList) {
                printWriter.println(s.getId() + ", " + s.getFirstName() + ", " + s.getLastName() + ", " + s.getDob() + ", " + s.getPhone());
            }
            printWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }




        return studentList;
    }

    public static int getLastId(){
        final String dbFileName = System.getProperty("user.dir") + File.separator + "db" + File.separator + DB_FILE_NAME;
        final File file = new File(dbFileName);
        if(!file.exists()){
            System.out.println("File not exist");
        }
        int lastId = 0;
        try {
            String line = "";

            String [] values ;
            int id ;
            BufferedReader bufferedReader  = new BufferedReader(new FileReader(file));


            while ((line = bufferedReader.readLine())!= null){

                 values= line.split(",");
                 id = Integer.parseInt(values[0]);
                 lastId = Math.max(id,lastId);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastId;
    }

}
