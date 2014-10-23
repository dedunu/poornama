package com.poornama.api.logging;

import org.apache.log4j.Logger;

public class GlobalLogger {

    private static Logger logger = Logger.getLogger(GlobalLogger.class);

    protected GlobalLogger() {

    }

    public static Logger getLogger() {
        return logger;
    }
}
