package com.itheima.bos.service.impl.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.base.FixedareaRepository;
import com.itheima.bos.dao.base.SubareaRepository;
import com.itheima.bos.domain.base.FixedArea;
import com.itheima.bos.domain.base.SubArea;
import com.itheima.bos.service.base.SubareaService;

/**  
 * ClassName:SubareaServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     Nov 3, 2017 10:20:15 PM <br/>       
 */
@Service
@Transactional
public class SubareaServiceImpl implements SubareaService {
    
    @Autowired
    private SubareaRepository subareaRepository;
    @Autowired
    private FixedareaRepository fixedareaRepository;

    @Override
    public void save(SubArea model) {
        subareaRepository.save(model);
    }

    @Override
    public Page<SubArea> findByPage(Pageable pageable) {
        return subareaRepository.findAll(pageable);
    }

    @Override
    public List<SubArea> findUnAssociatedSubareas() {
        return subareaRepository.findByFixedAreaIsNull();
    }

    @Override
    public List<SubArea> findSubareaAssociated2Fixedarea(Long id) {
        if(id != null) {
            FixedArea fixedArea = fixedareaRepository.findOne(id);
            if(fixedArea != null) {
                List<SubArea> list = subareaRepository.findByFixedArea(id);
                return list;
            }
        }
        return null;
    }

}
  
