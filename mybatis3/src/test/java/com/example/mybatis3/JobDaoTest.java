package com.example.mybatis3;

import com.example.mybatis3.job.Job;
import com.example.mybatis3.job.JobDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JobDaoTest {
    @Autowired
    private JobDao jobdao;
    @Autowired
    private JobDao jobDao;

    // @Test
    public void saveTest() {
        Job j = Job.builder().title("테스트해보기").build();
        int result = jobdao.save(j);
        assertEquals(1, result);
    }

    // @Test
    public void findAllTest() {
        assertEquals(1, jobdao.findAll().size());
    }

    // @Test
    public void findByJnoTest() {
        assertNotNull(jobDao.findByJno(1));
        assertNull(jobDao.findByJno(100));
    }

    // @Test
    public void updateTest() {
        int result = jobDao.update("테스트!!", 1);
        assertEquals(1, result);
        result = jobDao.update("어려워", 100);
        assertEquals(0, result);
    }

    @Transactional
    @Test
    public void deleteTest() {
        int result = jobDao.delete(1);
        assertEquals(1, result);
        result = jobdao.delete(100);
        assertEquals(0, result);
    }

}


















