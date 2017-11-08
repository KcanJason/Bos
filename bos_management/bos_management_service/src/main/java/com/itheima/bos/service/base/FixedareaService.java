package com.itheima.bos.service.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itheima.bos.domain.base.FixedArea;

/**  
 * ClassName:FixedareaService <br/>  
 * Function:  <br/>  
 * Date:     Nov 4, 2017 10:24:32 AM <br/>       
 */
public interface FixedareaService {

    void save(FixedArea model);

    Page<FixedArea> findByPage(Pageable pageable);

    void assignCourier2Fixedarea(Long id, Long courierId, Long takeTimeId);

    void assignSubareas2Fixedarea(Long id, List<Long> subareaIds);

}
  
