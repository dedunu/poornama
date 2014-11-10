package com.poornama.api.configuration;

public class ConfigurationProvider {
    private static ConfigurationReader reader = new ConfigurationReader();

    protected ConfigurationProvider() {

    }

    public static ConfigurationReader getConfigurationReader() {
        return reader;
    }
}
