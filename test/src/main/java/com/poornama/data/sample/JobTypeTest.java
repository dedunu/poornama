package com.poornama.data.sample;

import com.poornama.api.objects.JobType;
import com.poornama.data.dao.JobTypeDAO;
import org.junit.Test;

/**
 * Created by ddhananjaya on 5/16/15.
 */
public class JobTypeTest {
    @Test
    public void runTest(){
        JobTypeDAO jobTypeDAO = new JobTypeDAO();

        JobType jobType1 = new JobType();
        jobType1.setDisplayName("Import");
        jobType1.setName("import");
        jobTypeDAO.create(jobType1);

        JobType jobType2 = new JobType();
        jobType2.setDisplayName("Export");
        jobType2.setName("export");
        jobTypeDAO.create(jobType2);

        JobType jobType3 = new JobType();
        jobType3.setDisplayName("Less than Container Load - LCL");
        jobType3.setName("lcl");
        jobTypeDAO.create(jobType3);
    }
}
