package com.itheima.bos.web.action.base;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.data.domain.Page;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**  
 * ClassName:CommonAction <br/>  
 * Function: 作为所有的Action的父类 <br/>  
 * Date:     Nov 3, 2017 11:46:54 AM <br/>       
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

    private T model;
    
    public BaseAction() {
        // 获取子类的字节码文件对象
        Class<? extends BaseAction> clazz = this.getClass();
        // 获取该子类集成父类
        Type type = clazz.getGenericSuperclass();
        ParameterizedType ptype = (ParameterizedType) type;
        Type[] types = ptype.getActualTypeArguments();
        Class realclass = (Class) types[0];
        try {
            this.model = (T) realclass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();  
        }
    }
    
    protected Integer page;
    protected Integer rows;
    
    public void page2Json(Page<T> page,String[] excludes) throws IOException {
        List<T> rows = page.getContent();
        long total = page.getTotalElements();
        Map<String,Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", rows);
        JsonConfig config = new JsonConfig();
        config.setExcludes(excludes);
        String json = JSONObject.fromObject(map,config).toString();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
    }
    
    public void list2Json(List list,String[] excludes) throws IOException {
        JsonConfig config = new JsonConfig();
        config.setExcludes(excludes);
        String json = JSONArray.fromObject(list,config).toString();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
    }
    
    @Override
    public T getModel() {
        return model;
    }

    public void setPage(Integer page) {
        this.page = page;
    }


    public void setRows(Integer rows) {
        this.rows = rows;
    }

}
  
