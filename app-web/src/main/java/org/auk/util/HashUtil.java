package org.auk.util;

import com.google.common.hash.Hashing;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

    private final static String salt = "abc123guegueP1C1gue";

    public static String passwordHash(String originalPassword) {
        String hashedPassword = "";

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(originalPassword.getBytes(StandardCharsets.UTF_8));
            hashedPassword = DatatypeConverter.printHexBinary(digest).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hashedPassword;
    }

    public static boolean passwordVerify(String originalPassword, String hashedPassword) {
        return passwordHash(originalPassword).equals(hashedPassword);
    }
}
