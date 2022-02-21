package com.fandeni.pastaandbastaBE.conf;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Arrays;

public class PasswordUtils {
    public static String encrypt(String passwordToHash){
        try {
            SecureRandom sr = null;
            sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
            byte[] saltb = new byte[16];
            sr.nextBytes(saltb);
            String salt = Arrays.toString(saltb);
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            System.out.println(sb);
            return sb.toString();
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            e.printStackTrace();
            return null;
        }
    }
}
