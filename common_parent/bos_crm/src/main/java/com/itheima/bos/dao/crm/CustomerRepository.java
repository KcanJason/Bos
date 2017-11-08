package com.itheima.bos.dao.crm;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.itheima.bos.domain.crm.Customer;

/**  
 * ClassName:CustomerRepository <br/>  
 * Function:  <br/>  
 * Date:     Nov 5, 2017 10:51:09 AM <br/>       
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByFixedAreaIdIsNull();
    
    List<Customer> findByfixedAreaId(String fixedAreaId);

    @Modifying
    @Query("update Customer set fixedAreaId = null where fixedAreaId = ?")
    void setFixedareaNullByFixedareaId(String fixedareaId);

    @Modifying
    @Query("update Customer set fixedAreaId = ? where id = ?")
    void assignCustomer2Fixedarea(String fixedareaId, Long customerId);
}
  
