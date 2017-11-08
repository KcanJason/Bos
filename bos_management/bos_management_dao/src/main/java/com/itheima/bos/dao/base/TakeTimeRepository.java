package com.itheima.bos.dao.base;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itheima.bos.domain.base.TakeTime;

/**  
 * ClassName:TakeTimeRepository <br/>  
 * Function:  <br/>  
 * Date:     Nov 6, 2017 11:45:12 PM <br/>       
 */
public interface TakeTimeRepository extends JpaRepository<TakeTime, Long> {
    
    @Query("from TakeTime where status = 0")
    List<TakeTime> findByStatus();

}
  
