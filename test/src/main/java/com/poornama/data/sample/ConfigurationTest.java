package com.poornama.data.sample;

import com.poornama.api.objects.Configuration;
import com.poornama.data.dao.ConfigurationDAO;
import org.junit.Test;

/**
 * Created by dedunu on 7/22/15.
 */
public class ConfigurationTest {
    @Test
    public void runTest(){
        ConfigurationDAO configurationDAO = new ConfigurationDAO();

        Configuration configuration1 = new Configuration();
        configuration1.setName("driverCommission");
        configuration1.setValue("0.08");
        configurationDAO.create(configuration1);

        Configuration configuration2 = new Configuration();
        configuration2.setName("cleanerCommission");
        configuration2.setValue("0.05");
        configurationDAO.create(configuration2);

        Configuration configuration3 = new Configuration();
        configuration3.setName("driverBasicSalary");
        configuration3.setValue("30000");
        configurationDAO.create(configuration3);

        Configuration configuration4 = new Configuration();
        configuration4.setName("cleanerBasicSalary");
        configuration4.setValue("20000");
        configurationDAO.create(configuration4);

        Configuration configuration5 = new Configuration();
        configuration5.setName("employeeEPF");
        configuration5.setValue("0.08");
        configurationDAO.create(configuration5);

        Configuration configuration6 = new Configuration();
        configuration6.setName("companyEPF");
        configuration6.setValue("0.12");
        configurationDAO.create(configuration6);

        Configuration configuration7 = new Configuration();
        configuration7.setName("companyETF");
        configuration7.setValue("0.03");
        configurationDAO.create(configuration7);
    }
}
