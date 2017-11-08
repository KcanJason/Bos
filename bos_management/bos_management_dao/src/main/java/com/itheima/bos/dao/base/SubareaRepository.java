package com.itheima.bos.dao.base;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.itheima.bos.domain.base.SubArea;

/**  
 * ClassName:SubareaRepository <br/>  
 * Function:  <br/>  
 * Date:     Nov 3, 2017 10:22:31 PM <br/>       
 */
public interface SubareaRepository extends JpaRepository<SubArea, Long> {

    List<SubArea> findByFixedAreaIsNull();
    
    @Query(value = "select * from t_sub_area where c_fixedarea_id = ?",nativeQuery = true)
    List<SubArea> findByFixedArea(Long id);

}
  
