package org.auk.utils;

import org.auk.models.Student;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Faker {

    private String path ;

    public Faker(String path) {
        this.path = path ;
    }


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
            student.setDob(dateOfBirth.getTime());

            students.add(student);
        }

        return students;
    }

    /**
     * Reads input stream from an existing text file
     * TODO: implement FileSystem DataSource
     * @param numberOfStudents
     */
    public static List<Student> buildMockStudentsFromFile(int numberOfStudents) {
//        for (Map.Entry<?,?> e : System.getProperties().entrySet()) {
//            System.out.println(String.format("%s = %s", e.getKey(), e.getValue()));
//        }

        List<Student> studentList = new ArrayList<>();

//        System.lineSeparator()
        String fileName = System.getProperty("user.dir") + File.separator + "db/database.txt";

        final File file = new File(fileName);

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
//            BufferedReader reader = new BufferedReader(new FileReader(fileName, StandardCharsets.UTF_8));
//            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream, StandardCharsets.UTF_8));

//            var sb = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                sb.append(line);
//            }
//            System.out.println(sb.toString());

            // JAVA 8 File reading API
//            String content = Files.readString(Path.of(fileName));
            List<String> lines = Files.readAllLines(Path.of(fileName));

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

    public void writeStudentsInFiles(Student s) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.write(s.toString() + "\n");
            System.out.println();
            System.out.println("Student : " + s + " added succesfully !");
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String findLastLine() {
        String last = "" ;
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            String lineReader = "" ;
            while ((lineReader = br.readLine()) != null) {
                last = lineReader ;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return last ;
    }
}