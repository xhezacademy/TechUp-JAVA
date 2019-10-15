package org.auk.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileDataSourceImpl {

    private final static String DB_FILE_NAME = "database.txt";

    private final String dbFileName;

    FileDataSourceImpl() {
        dbFileName = System.getProperty("user.dir") + File.separator + "db" + File.separator + DB_FILE_NAME;
    }

    String getDbFileName() {
        return dbFileName;
    }

    /**
     * Inserts a new Student record to the FileSystem data store
     * @param data The data to be written
     * @param append To new the end of existing file
     * @return Was the file write attempt successful or not
     */
    public boolean write(String data, boolean append) {
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
