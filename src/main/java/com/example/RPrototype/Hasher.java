package com.example.RPrototype;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {

    public static String getSHA256Hash(String input) {
        try {
            // Creëer een MessageDigest-object voor de SHA-256-hashalgoritme
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Bereken de hash van de input door het te encode in bytes en vervolgens te hashen
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            // Maak een StringBuilder-object om de hexadecimale representatie van de hash op te bouwen
            StringBuilder hexString = new StringBuilder();

            // Bouw de hexadecimale representatie van de hash op
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            // Geef de hexadecimale representatie van de hash terug als resultaat
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Gooi een runtime exception als het SHA-256-algoritme niet beschikbaar is
            throw new RuntimeException(e);
        }
    }


}


