package com.poornama.api.security;

import com.poornama.api.logging.GlobalLogger;
import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by dedunu on 10/23/14.
 */
public class PasswordHash {

    private static Logger log = GlobalLogger.getLogger();
    private static String className = PasswordHash.class.getName();

    public String getHash(String userName, String password) {
        String result = null;
        String plainText = userName + password;

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(plainText.getBytes(), 0, plainText.length());
            result = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            log.error("[" + className + "] getHash: NoSuchAlgorithmException");
        }

        log.debug("[" + className + "] getHash");

        return result;
    }
}