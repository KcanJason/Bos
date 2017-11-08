package com.itheima.bos.service.crm;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.itheima.bos.domain.crm.Customer;

/**  
 * ClassName:CustomerService <br/>  
 * Function:  <br/>  
 * Date:     Nov 6, 2017 9:06:35 AM <br/>       
 */
public interface CustomerService {

    @GET
    @Path("/customer")
    @Produces({MediaType.APPLICATION_XML})
    List<Customer> findAll();
    
    @GET
    @Path("/findUnAssociatedCustomers")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    List<Customer> findUnAssociatedCustomers();
    
    @GET
    @Path("/findCustomerAssociated2Fixedarea")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    List<Customer> findCustomerAssociated2Fixedarea(@QueryParam("id")String fixedAreaId);
    
    @PUT
    @Path("/assignCustomers2Fixedarea")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    void assignCustomers2Fixedarea(@QueryParam("fixedareaId")String fixedareaId,@QueryParam("customerIds")List<Long> customerIds);
    
    @POST
    @Path("/customer")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    void save(Customer customer);
    
}
  
