package com.itheima.bos.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.itheima.bos.domain.base.Standard;

/**  
 * ClassName:StandardRepository <br/>  
 * Function:  <br/>  
 * Date:     Nov 1, 2017 6:43:40 PM <br/>       
 */
public interface StandardRepository extends JpaRepository<Standard, Integer> {

    @Modifying
    @Query("delete from Standard where id = ?")
    void deleteById(long parseLong);

}
  
