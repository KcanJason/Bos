package com.itheima.bos.service.impl.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.base.CourierRepository;
import com.itheima.bos.dao.base.FixedareaRepository;
import com.itheima.bos.dao.base.SubareaRepository;
import com.itheima.bos.dao.base.TakeTimeRepository;
import com.itheima.bos.domain.base.Courier;
import com.itheima.bos.domain.base.FixedArea;
import com.itheima.bos.domain.base.SubArea;
import com.itheima.bos.domain.base.TakeTime;
import com.itheima.bos.service.base.FixedareaService;

/**
 * ClassName:FixedareaServiceImpl <br/>
 * Function: <br/>
 * Date: Nov 4, 2017 10:25:21 AM <br/>
 */
@Service
@Transactional
public class FixedareaServiceImpl implements FixedareaService {

    @Autowired
    private FixedareaRepository fixedareaRepository;
    @Autowired
    private CourierRepository courierRepository;
    @Autowired
    private TakeTimeRepository takeTimeRepository;
    @Autowired
    private SubareaRepository subareaRepository;

    @Override
    public void save(FixedArea model) {
        fixedareaRepository.save(model);
    }

    @Override
    public Page<FixedArea> findByPage(Pageable pageable) {
        return fixedareaRepository.findAll(pageable);
    }

    @Override
    public void assignCourier2Fixedarea(Long id, Long courierId, Long takeTimeId) {
        // 获取指定的定区对象
        FixedArea fixedArea = fixedareaRepository.findOne(id);
        // 获取指定的快递员对象
        Courier courier = courierRepository.findOne(courierId);
        // 获取指定的收派时间
        TakeTime takeTime = takeTimeRepository.findOne(takeTimeId);
        fixedArea.getCouriers().add(courier);
        courier.setTakeTime(takeTime);
    }

    @Override
    public void assignSubareas2Fixedarea(Long id, List<Long> subareaIds) {
        FixedArea fixedArea = fixedareaRepository.findOne(id);
        if (subareaIds != null && subareaIds.size() > 0) {
            for (Long subareaId : subareaIds) {
                SubArea subArea = subareaRepository.findOne(subareaId);
                subArea.setFixedArea(fixedArea);
            }
        }
    }

}
