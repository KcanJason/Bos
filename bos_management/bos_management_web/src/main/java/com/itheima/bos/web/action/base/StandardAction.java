package com.itheima.bos.web.action.base;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
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

import com.itheima.bos.domain.base.Standard;
import com.itheima.bos.service.base.StandardService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**  
 * ClassName:StandardAction <br/>  
 * Function:  <br/>  
 * Date:     Nov 1, 2017 7:55:30 PM <br/>       
 */

@Controller
@Namespace("/")
@Scope("prototype")
@ParentPackage("struts-default")
public class StandardAction extends BaseAction<Standard> {

    private static final long serialVersionUID = 1L;
    
    @Autowired
    private StandardService standardService;
    private String ids;     // 包含每列选中的id的字符串
    
    @Action(value="standardAction_save",results= {@Result(name="success",location="/pages/base/standard.html",type="redirect")})
    public String save() {
        standardService.save(getModel());
        return SUCCESS;
    }

    @Action("standardAction_findByPage")
    public String findByPage() throws IOException {
        Pageable pageable = new PageRequest(page - 1, rows);
        Page<Standard> page = standardService.findByPage(pageable);
        page2Json(page, null);
        return NONE;
    }
    
    @Action("standardAction_findAll")
    public String findAll() throws IOException {
        List<Standard> list = standardService.findAll();
        list2Json(list, null);
        return NONE;
    }

    @Action(value="standardAction_batchDel",results= {@Result(name="success",location="/pages/base/standard.html",type="redirect")})
    public String batchDel() {
        standardService.batchDel(ids);
        return SUCCESS;
    }
    
    public void setIds(String ids) {
        this.ids = ids;
    }
    
}
  
