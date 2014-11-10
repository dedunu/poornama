package com.poornama.test;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.security.PasswordHash;
import org.apache.log4j.Logger;

/**
 * Created by dedunu on 11/10/14.
 */
public class Main {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = Main.class.getName();

    public static void main(String args[]) throws Exception {
        PasswordHash passwordHash = new PasswordHash();
        System.out.println(passwordHash.getHash("dedunu dedunu","rootroot"));
        // e46aa8024608679940af3b88ee13c14d

        //f8aa18710089408a96e4cd205a9576da
    }
}
