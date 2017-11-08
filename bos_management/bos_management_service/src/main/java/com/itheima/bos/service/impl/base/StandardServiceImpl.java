package com.itheima.bos.service.impl.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.base.StandardRepository;
import com.itheima.bos.domain.base.Standard;
import com.itheima.bos.service.base.StandardService;

/**  
 * ClassName:StandardServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     Nov 1, 2017 8:08:10 PM <br/>       
 */
@Service
@Transactional
public class StandardServiceImpl implements StandardService {

    @Autowired
    private StandardRepository standardRepository;
    
    @Override
    public void save(Standard model) {
        standardRepository.save(model);
    }

    @Override
    public Page<Standard> findByPage(Pageable pageable) {
        return standardRepository.findAll(pageable);
    }

    @Override
    public List<Standard> findAll() {
        return standardRepository.findAll();
    }

    @Override
    public void batchDel(String ids) {
        if(!ids.isEmpty()) {
            String[] arr = ids.split(",");
            for (String id : arr) {
                standardRepository.deleteById(Long.parseLong(id));
            }
        }
    }

}
  
