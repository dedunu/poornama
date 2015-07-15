package com.poornama.data.sample;

import com.poornama.api.objects.Job;
import com.poornama.data.dao.JobDAO;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by ddhananjaya on 7/14/15.
 */
public class JobTest {

    @Test
    public void runTest(){
        JobDAO jobDAO = new JobDAO();

        Job job1 = new Job();
        job1.setHourlyDetentionCharges(new BigDecimal(100));

    }
}
