package com.itheima.bos.web.test;

import java.util.Collection;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.junit.Test;

import com.itheima.bos.domain.crm.Customer;



/**  
 * ClassName:CustomerRepositoryTest <br/>  
 * Function:  <br/>  
 * Date:     Nov 6, 2017 9:15:20 AM <br/>       
 */
public class CustomerRepositoryTest {
    
    @Test
    public void test1() {
        Collection<? extends Customer> collection = WebClient
                .create("http://localhost:8180/bos_crm/webservice/customerService/customer")
                .accept(MediaType.APPLICATION_XML)// 指定对方传过来的数据格式
               // .type(MediaType.APPLICATION_JSON)// 指定传输给对方的数据格式
                .getCollection(Customer.class);

        for (Customer customer : collection) {
            System.out.println(customer);
        }
    }
    
    @Test
    public void test2() {
        Customer c =new Customer();
        c.setMobilePhone("234234234");
        c.setPassword("234");
                WebClient.create("http://localhost:8180/bos_crm/webservice/customerService/customer")
                        .type(MediaType.APPLICATION_JSON).post(c);
        }


}
  
