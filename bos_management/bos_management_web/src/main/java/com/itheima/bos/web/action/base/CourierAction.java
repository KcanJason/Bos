package com.itheima.bos.web.action.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.base.Courier;
import com.itheima.bos.domain.base.Standard;
import com.itheima.bos.service.base.CourierService;


/**  
 * ClassName:CourierAction <br/>  
 * Function:  <br/>  
 * Date:     Nov 1, 2017 9:50:16 PM <br/>       
 */
@Controller
@Namespace("/")
@Scope("prototype")
@ParentPackage("struts-default")
public class CourierAction extends BaseAction<Courier> {

    private static final long serialVersionUID = 1L;
    
    @Autowired
    private CourierService courierService;
    private String ids;
    
    @Action(value="courierAction_save",results= {@Result(name="success",location="/pages/base/courier.html",type="redirect")})
    public String save() {
        getModel().setDeltag('0');
        courierService.save(getModel());
        return SUCCESS;
    }
    
    @Action("courierAction_findByPage")
    public String findByPage() throws IOException {
        final String courierNum = getModel().getCourierNum();
        final Standard standard = getModel().getStandard();
        final String company = getModel().getCompany();
        final String type = getModel().getType();
        // 创建封装条件查询的对象
        Specification<Courier> specification = new Specification<Courier>() {
            // root:理解为泛型内的对象
            // cb:构造条件查询的对象
            @Override
            public Predicate toPredicate(Root<Courier> root, CriteriaQuery<?> query,
                    CriteriaBuilder cb) {
                  List<Predicate> list = new ArrayList<>();
                  if(StringUtils.isNotEmpty(courierNum)) {
                      Predicate p1 = cb.equal(root.get("courierNum").as(String.class),courierNum);
                      list.add(p1);
                  }
                  if(standard!=null && StringUtils.isNotEmpty(standard.getName())) {
                      Join<Object, Object> join = root.join("standard");
                      Predicate p2 = cb.equal(join.get("name").as(String.class), standard.getName());
                      list.add(p2);
                  }
                  if(StringUtils.isNotEmpty(company)) {
                      Predicate p3 = cb.like(root.get("company").as(String.class), "%"+company+"%");
                      list.add(p3);
                  }
                  if(StringUtils.isNotEmpty(type)) {
                      Predicate p4 = cb.equal(root.get("type").as(String.class), type);
                      list.add(p4);
                  }
                  if(list.size() > 0) {
                      Predicate[] arr = new Predicate[list.size()];
                      Predicate[] array = list.toArray(arr);
                      return cb.and(array);
                  }else {
                      return null;
                  }
            }};
            
        Pageable pageable = new PageRequest(page - 1, rows);
        Page<Courier> page = courierService.findByPage(specification,pageable);
        page2Json(page, new String[] {"fixedAreas","takeTime"});
        return NONE;
    }
    
    @Action(value="courierAction_batchDel",results= {@Result(name="success",location="/pages/base/courier.html",type="redirect")})
    public String batchDel() {
        courierService.batchDel(ids);
        return SUCCESS;
    }
    
    @Action("courierAction_findAll")
    public String findAll() throws IOException {
        List<Courier> list = courierService.findAll();
        list2Json(list, new String[] {"fixedAreas"});
        return NONE;
    }
    
    

    public void setIds(String ids) {
        this.ids = ids;
    }
}
  
