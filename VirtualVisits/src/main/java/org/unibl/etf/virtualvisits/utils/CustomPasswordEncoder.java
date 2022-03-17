package org.unibl.etf.virtualvisits.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CustomPasswordEncoder implements PasswordEncoder {

    @Value("${hash-algorithm}")
    private String hashingAlgorithm;

    @Override
    public String encode(CharSequence rawPassword) {
        return hashPassword(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(hashPassword(rawPassword.toString()));
    }

    //function to convert byte array to hex string
    private String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for(byte b: a)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }

    //function to hash password
    private String hashPassword(String rawPassword){
        try {
            //hashing password with SHA-256
            MessageDigest md = MessageDigest.getInstance(hashingAlgorithm);
            md.update(rawPassword.toString().getBytes());
            byte[] digest = md.digest();
            return byteArrayToHex(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
