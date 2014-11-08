package com.poornama;

import com.poornama.api.logging.GlobalLogger;
import org.apache.log4j.Logger;
import org.junit.Ignore;

import java.util.HashMap;

/**
 * Created by dedunu on 11/6/14.
 */
@Ignore
public class Test {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = Test.class.getName();

    public static void main(String args[]) throws Exception {
        String string = "1_5|6_1|8_2|8_3|";
        String array[] = string.split("\\|");
        HashMap<String, Integer> attendanceMap = new HashMap<>();
        for (String text : array) {
            attendanceMap.put(text,1);
        }
        System.out.println(attendanceMap.get("1_6"));
    }
}
