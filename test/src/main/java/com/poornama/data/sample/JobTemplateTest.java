package com.poornama.data.sample;

import com.poornama.api.objects.Client;
import com.poornama.api.objects.JobTemplate;
import com.poornama.api.objects.JobType;
import com.poornama.data.dao.ClientDAO;
import com.poornama.data.dao.JobTemplateDAO;
import com.poornama.data.dao.JobTypeDAO;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by ddhananjaya on 6/11/15.
 */
public class JobTemplateTest {
    @Test
    public void runTest(){
        JobTemplateDAO jobTemplateDAO = new JobTemplateDAO();
        ClientDAO clientDAO = new ClientDAO();
        JobTypeDAO jobTypeDAO = new JobTypeDAO();

        JobType jobType = jobTypeDAO.getById(1);

        Client client = clientDAO.getByOrganizationName("MAS Active");

        JobTemplate jobTemplate1 = new JobTemplate();
        jobTemplate1.setDisplayName("40ft. Colombo to Mawathagama (MAS)");
        jobTemplate1.setClient(client);
        jobTemplate1.setJobType(jobType);
        jobTemplate1.setHireCharges(new BigDecimal(16500));
        jobTemplate1.setLabourCharges(new BigDecimal(3000));
        jobTemplate1.setDailyContainerCharges(new BigDecimal(1250));
        jobTemplate1.setHourlyDetentionCharges(new BigDecimal(100));
        jobTemplate1.setFreeHours(24);
        jobTemplate1.setFromLocation("Colombo");
        jobTemplate1.setToLocation("Mawathagama");
        jobTemplate1.setContainerSize(40);
        jobTemplateDAO.create(jobTemplate1);

        jobType = jobTypeDAO.getById(2);

        JobTemplate jobTemplate2 = new JobTemplate();
        jobTemplate2.setDisplayName("20ft. Colombo to Mawathagama (MAS)");
        jobTemplate2.setClient(client);
        jobTemplate2.setJobType(jobType);
        jobTemplate2.setHireCharges(new BigDecimal(13500));
        jobTemplate2.setLabourCharges(new BigDecimal(2500));
        jobTemplate2.setDailyContainerCharges(new BigDecimal(1250));
        jobTemplate2.setHourlyDetentionCharges(new BigDecimal(100));
        jobTemplate2.setFreeHours(24);
        jobTemplate2.setFromLocation("Colombo");
        jobTemplate2.setToLocation("Mawathagama");
        jobTemplate2.setContainerSize(20);
        jobTemplateDAO.create(jobTemplate2);

        client = clientDAO.getByOrganizationName("Universal Freighters");

        jobType = jobTypeDAO.getById(3);

        JobTemplate jobTemplate3 = new JobTemplate();
        jobTemplate3.setDisplayName("20ft. Colombo to Koggala (UF)");
        jobTemplate3.setClient(client);
        jobTemplate3.setJobType(jobType);
        jobTemplate3.setHireCharges(new BigDecimal(13500));
        jobTemplate3.setLabourCharges(new BigDecimal(2500));
        jobTemplate3.setDailyContainerCharges(new BigDecimal(1250));
        jobTemplate3.setHourlyDetentionCharges(new BigDecimal(100));
        jobTemplate3.setFreeHours(24);
        jobTemplate3.setFromLocation("Colombo");
        jobTemplate3.setToLocation("Koggala");
        jobTemplate3.setContainerSize(20);
        jobTemplateDAO.create(jobTemplate3);
    }
}
