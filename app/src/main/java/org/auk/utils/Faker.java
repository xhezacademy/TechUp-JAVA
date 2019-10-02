package org.auk.utils;

import org.auk.models.Student;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class Faker {

    public static List<Student> buildMockStudents(int studentCount) {
        List<Student> members = new ArrayList<>(studentCount);

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

            members.add(student);
        }

        return members;
    }
}
