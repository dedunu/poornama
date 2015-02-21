package com.poornama.test;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.OrganizationType;
import com.poornama.data.sample.MainSampleTest;
import com.poornama.data.sample.OrganizationTypeTest;

import org.apache.log4j.Logger;

/**
 * Created by dedunu on 11/10/14.
 */
public class Main {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = Main.class.getName();

    public static void main(String args[]) throws Exception {
        MainSampleTest.main(null);
    }
}
