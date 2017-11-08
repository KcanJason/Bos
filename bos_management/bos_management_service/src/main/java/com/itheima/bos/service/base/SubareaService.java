package com.itheima.bos.service.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itheima.bos.domain.base.FixedArea;
import com.itheima.bos.domain.base.SubArea;

/**  
 * ClassName:SubareaService <br/>  
 * Function:  <br/>  
 * Date:     Nov 3, 2017 10:19:44 PM <br/>       
 */
public interface SubareaService {

    void save(SubArea model);

    Page<SubArea> findByPage(Pageable pageable);

    List<SubArea> findUnAssociatedSubareas();

    List<SubArea> findSubareaAssociated2Fixedarea(Long id);


}
  
