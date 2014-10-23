package com.poornama.api.configuration;

import com.poornama.api.logging.GlobalLogger;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    private static Logger log = GlobalLogger.getLogger();
    private static String className = ConfigurationReader.class.getName();

    protected ConfigurationReader() {

    }

    public String getConfigurationPath() {
        log.debug("[" + className + "] getConfigurationPath");
        return "/home/dedunu/code/poornama/code/poornama_home/conf/";
        //return System.getProperty("POORNAMA_HOME") + "/conf/";
    }

    public String getProperties(String propertyPath,
                                String configurationFileName) {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder builder = null;
        Document document = null;
        XPath xPath = XPathFactory.newInstance().newXPath();
        String fileName = this.getConfigurationPath() + configurationFileName;
        String propertyValue = null;

        try {
            builder = builderFactory.newDocumentBuilder();
            log.debug("[" + className + "] getProperties: created new DocumentBuilder");
        } catch (ParserConfigurationException e) {
            log.error("[" + className + "] getProperties: creating new DocumentBuilder");
            e.printStackTrace();
        }

        try {
            document = builder.parse(new FileInputStream(fileName));
            log.debug("[" + className + "] getProperties: created new Document");
        } catch (SAXException e) {
            log.error("[" + className + "] getProperties: SAXException in creating new document");
            e.printStackTrace();
        } catch (IOException e) {
            log.error("[" + className + "] getProperties: IOException in creating new document");
            e.printStackTrace();
        }

        try {
            propertyValue = xPath.compile(propertyPath).evaluate(document);
            log.debug("[" + className + "] getProperties: Retrieved propertyValue");
        } catch (XPathExpressionException e) {
            log.error("[" + className + "] getProperties: XPathExpressionException in retrieving propertyValue");
            e.printStackTrace();
        }
        log.debug("[" + className + "] getProperties");
        return propertyValue;
    }

    public Properties getEJBProperties(String propertiesFile) {
        Properties properties = new Properties();
        String fileName = this.getConfigurationPath() + propertiesFile;
        try {
            properties.load(new FileInputStream(fileName));
            log.debug("[" + className + "] getEJBProperties: loaded propertiesFile - "
                    + propertiesFile);
        } catch (IOException ex) {
            log.error("[" + className + "] getEJBProperties: IOException in loading propertiesFile - "
                    + propertiesFile);
            ex.printStackTrace();
        }
        log.debug("[" + className + "] getEJBProperties");
        return properties;
    }
}
