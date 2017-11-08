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

import com.itheima.bos.domain.base.SubArea;
import com.itheima.bos.service.base.SubareaService;

/**  
 * ClassName:SubareaAction <br/>  
 * Function:  <br/>  
 * Date:     Nov 3, 2017 10:12:25 PM <br/>       
 */
@Controller
@Namespace("/")
@Scope("prototype")
@ParentPackage("struts-default")
public class SubareaAction extends BaseAction<SubArea> {

    private static final long serialVersionUID = 1L;
    
    @Autowired
    private SubareaService subareaService;
    private Long fixedAreaId;

    public void setFixedAreaId(Long fixedAreaId) {
        this.fixedAreaId = fixedAreaId;
    }

    @Action(value="subareaAction_save",results = {@Result(name = "success", location = "/pages/base/sub_area.html", type = "redirect")})
    public String save() {
        subareaService.save(getModel());
        return SUCCESS;
    }
    
    @Action("subareaAction_findByPage")
    public String findByPage() throws IOException{
        Pageable pageable = new PageRequest(page -1, rows);
        Page<SubArea> page = subareaService.findByPage(pageable);
        page2Json(page, new String[] {"area","fixedArea"});
        return NONE;
    }
    
    @Action("subareaAction_findUnAssociatedSubareas")
    public String findUnAssociatedSubareas() throws IOException {
        List<SubArea> list = subareaService.findUnAssociatedSubareas();
        list2Json(list, new String[] {"area","fixedArea"});
        return NONE;
    }
    
    @Action("subareaAction_findSubareaAssociated2Fixedarea")
    public String findSubareaAssociated2Fixedarea() throws IOException {
        List<SubArea> list = subareaService.findSubareaAssociated2Fixedarea(fixedAreaId);
        list2Json(list, new String[] {"area","fixedArea"});
        return NONE;
    }
    
}
  
