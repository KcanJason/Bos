package com.itheima.bos.service.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.itheima.bos.domain.base.Courier;

/**  
 * ClassName:CourierService <br/>  
 * Function:  <br/>  
 * Date:     Nov 1, 2017 9:55:45 PM <br/>       
 */
public interface CourierService {

    void save(Courier model);

    Page<Courier> findByPage(Specification<Courier> specification , Pageable pageable);

    void batchDel(String ids);

    List<Courier> findAll();

}
  
