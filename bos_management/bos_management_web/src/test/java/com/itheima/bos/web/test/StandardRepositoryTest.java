package com.itheima.bos.web.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itheima.bos.dao.base.StandardRepository;
import com.itheima.bos.domain.base.Standard;

/**  
 * ClassName:StandardRepositoryTest <br/>  
 * Function:  <br/>  
 * Date:     Nov 1, 2017 6:56:29 PM <br/>       
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class StandardRepositoryTest {

    @Autowired
    private StandardRepository standardRepository;
    
    @Test
    public void test() {
        Standard standard = new Standard();
        standard.setMaxLength(100);
        standard.setName("老蒋");
        standardRepository.save(standard); 
    }

}
  
