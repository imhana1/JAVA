package com.example.demo6;

import com.example.demo6.execise.EmpDao;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Equals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EmpDaoTest {
    @Autowired
    private EmpDao empDao;

   // @Test
    public void findJob1Test() {
        empDao.findJob1().forEach(a -> System.out.println(a));
        assertEquals(5, empDao.findJob1().size());
    }

    //@Test
    public void findEmpByDeptno1Test() {
        // empDao.findEmpByDeptno1(10).forEach(a -> System.out.println(a));
        assertEquals(3, empDao.findEmpByDeptno1(10).size());
    }

    //@Test
    public void findEmpByCommIsNotNull1Test() {
        assertEquals(4, empDao.findEmpByCommIsNotNull1().size());
    }

    // @Test
    public void findJob2Test() {
        assertEquals(5, empDao.findJob2().size());
    }

    //@Test
    public void findEmpBySalLessThan2Test() {
        empDao.findEmpBySalLessThan2(5000).forEach(a -> System.out.println(a.get("JOB")));
        // Entity 로 리턴을 받았다면 a.getJob();
        // - 장점 : 오타는 절대 발생 하지 않음
        // - 단점 : 클래스를 만들어야 한다
        // Map 으로 리턴을 받으면 a.get("JOB")
        // - 장점 : 클래스를 만들 필요가 없다 (간편함)
        // - 단점 : 오타나면 나의 책임,,,
        assertEquals(14, empDao.findEmpBySalLessThan2(5000).size());
    }

    //@Test
    public void findEmpByDnameTest() {
        assertEquals(6, empDao.findEmpByDname("SALES").size());
    }

    // @Test
    public void findByEmpnoTest() {
        assertEquals(true, empDao.findByEmpno(7369).isPresent());
        assertEquals(true, empDao.findByEmpno(9000).isEmpty());
    }

    // @Test
    public void findByPositionTest() {
        assertEquals(5, empDao.findByPosition("POSITION").size());
    }

    //@Test
    public void findByTypeTest() {
        assertEquals(4, empDao.findByType("EMP_TYPE").size());
    }

    @Test
    public void findEmp2ByEmpnoTest() {
        System.out.println(empDao.findEmp2ByEmpno(19900101));
        assertEquals(true, empDao.findEmp2ByEmpno(19900101).isPresent());
        assertEquals(true, empDao.findEmp2ByEmpno(19990102).isEmpty());
    }

}

























