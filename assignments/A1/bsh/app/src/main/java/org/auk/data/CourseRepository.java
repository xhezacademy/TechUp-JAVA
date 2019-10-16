package org.auk.data;

import org.auk.models.Course;
import org.auk.models.Student;

import java.util.List;

public class CourseRepository implements DataSourceInterface<Course> {

    @Override
    public Course getById(int id) {
        return null;
    }

    @Override
    public List<Course> getAll() {
        return null;
    }

    @Override
    public void add(Course T) {

    }

    @Override
    public void remove(int id) {
        // no-op
    }
}
