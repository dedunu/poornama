package com.poornama.api.security;

import com.poornama.api.logging.GlobalLogger;
import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHash {

    private static Logger log = GlobalLogger.getLogger();
    private static String className = PasswordHash.class.getName();

    /**
     * Returned the hash value for the given username and password
     *
     * @param userName String
     * @param password String
     * @return Hashed password as a String
     */
    public String getHash(String userName, String password) {
        String result = null;
        String plainText = userName + password;

        try {
            // Initialize MessageDigest object with MD5 Algorithm
            MessageDigest digest = MessageDigest.getInstance("MD5");
            // Generated the hash
            digest.update(plainText.getBytes(), 0, plainText.length());
            // Convert it to a big integer
            result = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            log.error("[" + className + "] getHash: NoSuchAlgorithmException");
        }

        log.debug("[" + className + "] getHash");

        // Return the hashed text
        return result;
    }
}