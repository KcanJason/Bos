package com.itheima.bos.service.impl.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.base.CourierRepository;
import com.itheima.bos.domain.base.Courier;
import com.itheima.bos.service.base.CourierService;

/**  
 * ClassName:CourierServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     Nov 1, 2017 9:56:40 PM <br/>       
 */
@Service
@Transactional
public class CourierServiceImpl implements CourierService {

    @Autowired
    private CourierRepository courierRepository;
    
    @Override
    public void save(Courier model) {
        courierRepository.save(model);
    }

    @Override
    public Page<Courier> findByPage(Specification<Courier> specification , Pageable pageable) {
        return courierRepository.findAll(specification,pageable);
    }

    @Override
    public void batchDel(String ids) {
          if(!ids.isEmpty()) {
              String[] arr = ids.split(",");
              for (String id : arr) {
                courierRepository.updateById(Long.parseLong(id));
            }
          }
    }

    @Override
    public List<Courier> findAll() {
        return courierRepository.findByDeltag();
    }

}
  
