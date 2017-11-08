package com.itheima.bos.dao.base;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.itheima.bos.domain.base.Courier;

/**  
 * ClassName:CourierRepository <br/>  
 * Function:  <br/>  
 * Date:     Nov 1, 2017 9:59:37 PM <br/>       
 */
public interface CourierRepository extends JpaRepository<Courier, Long>,JpaSpecificationExecutor<Courier>{

    @Modifying
    @Query("update Courier set deltag = 1 where id = ?")
    void updateById(long parseLong);

    @Query("from Courier where deltag = 0")
    List<Courier> findByDeltag();

}
  
