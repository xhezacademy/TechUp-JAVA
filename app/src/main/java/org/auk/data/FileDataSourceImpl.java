package org.auk.data;

import org.auk.models.Student;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

public class FileDataSourceImpl {

    private final static String DB_FILE_NAME = "database.txt";

    private int nextRecordId;
    private final String dbFileName;

    FileDataSourceImpl() {
        dbFileName = System.getProperty("user.dir") + File.separator + "db" + File.separator + DB_FILE_NAME;

        Path filePath = Paths.get(System.getProperty("user.dir") + File.separator + "db");
        if (!Files.exists(filePath)) {
            try {
                Path dir = Files.createDirectories(filePath);
                Files.createFile(dir.resolve(DB_FILE_NAME));
                Logger.getGlobal().info("File created!");

//                Java IO
//                File f = new File(dbFileName);
//                file.getParentFile().mkdirs();
//                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    List<Student> readAll() {
        List<Student> studentList = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Path.of(getDbFileName()));

            for (var line : lines) {
                String[] values = line.split("\\s*,\\s*");

                Student student = new Student();
                student.setId(Integer.parseInt(values[0]));
                student.setFirstName(values[1].trim());
                student.setLastName(values[2].trim());
                student.setPhone(values[4]);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                Date dob = simpleDateFormat.parse(values[3]);
                student.setBirthday(dob);

                nextRecordId = Integer.parseInt(values[0]) + 1;
                studentList.add(student);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return studentList;
    }

    int getNextRecordId() {
        return nextRecordId;
    }

    private String getDbFileName() {
        return dbFileName;
    }

    /**
     * Inserts a new Student record to the FileSystem data store
     *
     * @param data   The data to be written
     * @param append To new the end of existing file
     * @return Was the file write attempt successful or not
     */
    boolean write(String data, boolean append) {
        try { // PrintWriter printWriter = new PrintWriter(new FileWriter(new File(dbFileName), append));
//            printWriter.write(data + System.getProperty("line.separator"));
//            printWriter.flush();

            Files.write(Path.of(getDbFileName()), (data + System.lineSeparator()).getBytes(), append ? StandardOpenOption.APPEND : StandardOpenOption.WRITE);
            System.out.println("The following data was successfully recorded: " + data);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void insertNewLine() {
        Path p = Paths.get(System.getProperty("user.dir") + "/db/database.txt");
        String s = System.lineSeparator() + "---" + System.lineSeparator();

        try {
            Files.write(p, s.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
