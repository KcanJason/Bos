package com.itheima.bos.service.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itheima.bos.domain.base.Standard;

/**  
 * ClassName:StandardService <br/>  
 * Function:  <br/>  
 * Date:     Nov 1, 2017 8:06:56 PM <br/>       
 */
public interface StandardService {

    void save(Standard model);

    Page<Standard> findByPage(Pageable pageable);

    List<Standard> findAll();

    void batchDel(String ids);

}
  
