package com.itheima.bos.service.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itheima.bos.domain.base.TakeTime;

/**  
 * ClassName:TakeTimeService <br/>  
 * Function:  <br/>  
 * Date:     Nov 6, 2017 11:46:04 PM <br/>       
 */
public interface TakeTimeService {

    List<TakeTime> findAll();

    Page<TakeTime> findByPage(Pageable pageable);

}
  
