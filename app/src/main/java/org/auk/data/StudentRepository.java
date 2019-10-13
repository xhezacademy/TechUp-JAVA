package org.auk.data;

import org.auk.models.Student;
import org.auk.utils.Faker;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class StudentRepository implements DataSourceInterface<Student> {

    private List<Student> studentList;

    public StudentRepository(int numberOfStudents) {

//     studentList = Faker.buildMockStudentsFromCollection(numberOfStudents);
//       studentList = Faker.buildMockStudentsFromFile(numberOfStudents);
        studentList = Faker.buildMockStudentsFromScanner();

//        studentList = Faker.buildMockStudentsFromCollection(numberOfStudents);
 //       studentList = Faker.buildMockStudentsFromFile();

    }

    @Override
    public Student getById(int id) {
        for (Student s : studentList) {
            if (s.getId() == id) {
                return s;
            }
        }

        return null;
    }

    @Override
    public List<Student> getAll() {

        return studentList;
    }

    public List<Student> getAllFromFile() {
        String DB_FILE_NAME = "database.txt";

        final String dbFileName = System.getProperty("user.dir") + File.separator + "db" + File.separator + DB_FILE_NAME;
        final File file = new File(dbFileName);
        if(!file.exists()){
            System.out.println("File not exist");
        }

        try {
            String line = "";

            String [] values ;
            String firstName;
            String lastName;
            String date;
            String phone;
            int id ;
            BufferedReader bufferedReader  = new BufferedReader(new FileReader(file));
studentList.clear();

            while ((line = bufferedReader.readLine())!= null){

                values= line.split(", ");
                id = Integer.parseInt(values[0]);
                firstName = values[1];
                date = values[3];
                phone = values[4];
                Student student = new Student();
                student.setId(id);
                student.setFirstName(firstName);
                DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
                Date datebo = (Date)formatter.parse(date);

                student.setDob(datebo);
                student.setPhone(phone);

                studentList.add(student);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    @Override
    public void add(Student s) {
        studentList.add(s);
    }

    @Override
    public void remove(int id) {
        studentList.remove(id);
    }
}
