package com.itheima.bos.service.impl.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.base.TakeTimeRepository;
import com.itheima.bos.domain.base.TakeTime;
import com.itheima.bos.service.base.TakeTimeService;

/**  
 * ClassName:TakeTimeServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     Nov 6, 2017 11:46:42 PM <br/>       
 */
@Service
@Transactional
public class TakeTimeServiceImpl implements TakeTimeService {
    
    @Autowired
    private TakeTimeRepository takeTimeRepository;
    

    @Override
    public List<TakeTime> findAll() {
        return takeTimeRepository.findByStatus();
    }


    @Override
    public Page<TakeTime> findByPage(Pageable pageable) {
        return takeTimeRepository.findAll(pageable);
    }

}
  
