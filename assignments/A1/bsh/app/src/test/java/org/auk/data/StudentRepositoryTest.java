package org.auk.data;

import org.auk.models.Student;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class StudentRepositoryTest {

    private StudentRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new StudentRepository(10);
    }

    @Test
    public void testGetAllReturnsNonEmptyCollection() {
        List<Student> students = repository.getAll();
        assertFalse(students.isEmpty());
    }

    @Test
    public void testIsInstanceOfStudent() {
        Object student = repository.getById(6);
        assertNotNull(student);
        assertThat(student, instanceOf(Student.class));
    }

    @Test
    public void testAddsStudentToCollection() {
        int size = repository.getAll().size();

        Student student = new Student();
        student.setFirstName("Jack");
        student.setLastName("Ma");
        student.setId(size + 1);

        repository.add(student);

        assertEquals(size+1, repository.getAll().size());
    }
}
