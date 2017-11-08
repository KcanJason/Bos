package com.itheima.bos.web.action.base;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.base.TakeTime;
import com.itheima.bos.service.base.TakeTimeService;

/**  
 * ClassName:TakeTimeAction <br/>  
 * Function:  <br/>  
 * Date:     Nov 6, 2017 11:48:52 PM <br/>       
 */
@Controller
@Namespace("/")
@Scope("prototype")
@ParentPackage("struts-default")
public class TakeTimeAction extends BaseAction<TakeTime> {

    private static final long serialVersionUID = 1L;

    @Autowired
    private TakeTimeService takeTimeService;
    
    @Action("takeTime_findAll")
    public String findAll() throws IOException {
        List<TakeTime> list = takeTimeService.findAll();
        list2Json(list, null);
        return NONE;
    }
    
    @Action("takeTime_findByPage")
    public String findByPage() throws IOException {
        Pageable pageable = new PageRequest(page -1, rows);
        Page<TakeTime> page = takeTimeService.findByPage(pageable);
        page2Json(page, null);
        return NONE;
    }
    
}
  
