package org.auk.data;

import org.auk.models.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class StudentRepository implements DataSourceInterface<Student> {

    private int nextRecordId;
    private FileDataSourceImpl dataSource;
    private List<Student> studentList;

    public StudentRepository(int numberOfStudents) {
//        studentList = Faker.buildMockStudentsFromCollection(numberOfStudents);
//        studentList = Faker.buildMockStudentsFromFile();
        dataSource = new FileDataSourceImpl();
        initializeStudentList();
    }

    private void initializeStudentList() {
        studentList = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Path.of(dataSource.getDbFileName()));

            for (var line : lines) {
                String[] values = line.split("\\s*,\\s*");

                Student student = new Student();
                student.setId(Integer.parseInt(values[0]));
                student.setFirstName(values[1].trim());
                student.setLastName(values[2].trim());
                student.setPhone(values[4]);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                Date dob = simpleDateFormat.parse(values[3]);
                student.setDob(dob);

                nextRecordId = Integer.parseInt(values[0]) + 1;
                studentList.add(student);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public int getNextRecordId() {
        return nextRecordId;
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

    @Override
    public void add(Student s) {
        studentList.add(s);
    }

    @Override
    public void remove(int id) {
        studentList.remove(id);
    }

    public void add(String studentData, boolean appendToFile) {
        dataSource.write(studentData, appendToFile);
        refreshList();
    }

    public void refreshList() {
        initializeStudentList();
    }
}
