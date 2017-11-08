package com.itheima.bos.service.impl.crm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.crm.CustomerRepository;
import com.itheima.bos.domain.crm.Customer;
import com.itheima.bos.service.crm.CustomerService;

/**  
 * ClassName:CustomerServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     Nov 6, 2017 9:07:00 AM <br/>       
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        List<Customer> list = customerRepository.findAll();
        return list;
    }

    @Override
    public List<Customer> findUnAssociatedCustomers() {
        return customerRepository.findByFixedAreaIdIsNull();
    }

    @Override
    public List<Customer> findCustomerAssociated2Fixedarea(String fixedAreaId) {
        return customerRepository.findByfixedAreaId(fixedAreaId);
    }

    @Override
    public void assignCustomers2Fixedarea(String fixedareaId, List<Long> customerIds) {
        // 将当前定区与所有客户解绑
        customerRepository.setFixedareaNullByFixedareaId(fixedareaId);
        // 将当前定区与当前的客户关联
        if(customerIds!=null && customerIds.size()>0) {
            for (Long customerId : customerIds) {
                customerRepository.assignCustomer2Fixedarea(fixedareaId,customerId);
            }
        }
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

}
  
