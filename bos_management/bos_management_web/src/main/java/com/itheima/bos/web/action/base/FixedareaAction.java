package com.itheima.bos.web.action.base;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
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

import com.itheima.bos.domain.base.FixedArea;
import com.itheima.bos.domain.crm.Customer;
import com.itheima.bos.service.base.FixedareaService;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

/**
 * ClassName:FixedareaAction <br/>
 * Function: <br/>
 * Date: Nov 4, 2017 10:17:23 AM <br/>
 */
@Controller
@Namespace("/")
@Scope("prototype")
@ParentPackage("struts-default")
public class FixedareaAction extends BaseAction<FixedArea> {

    private static final long serialVersionUID = 1L;

    @Autowired
    private FixedareaService fixedareaService;
    
    private List<Long> customerIds;
    private List<Long> subareaIds;
    
    private Long takeTimeId;
    private Long courierId;
    

    public void setCustomerIds(List<Long> customerIds) {
        this.customerIds = customerIds;
    }

    public void setCourierId(Long courierId) {
        this.courierId = courierId;
    }

    public void setTakeTimeId(Long takeTimeId) {
        this.takeTimeId = takeTimeId;
    }

    public void setSubareaIds(List<Long> subareaIds) {
        this.subareaIds = subareaIds;
    }

    @Action(value = "fixedAreaAction_save", results = {
            @Result(name = "success", location = "/pages/base/fixed_area.html", type = "redirect")})
    public String save() {
        fixedareaService.save(getModel());
        return NONE;
    }

    @Action("fixedareaAction_findByPage")
    public String findByPage() throws IOException {
        Pageable pageable = new PageRequest(page - 1, rows);
        Page<FixedArea> page = fixedareaService.findByPage(pageable);
        page2Json(page, new String[] {"subareas", "couriers"});
        return NONE;
    }

    @Action(value = "fixedareaAction_findUnAssociatedCustomers")
    public String findUnAssociatedCustomers() throws IOException {
        List<Customer> list = (List<Customer>) WebClient.create(
                "http://localhost:8180/bos_crm/webservice/customerService/findUnAssociatedCustomers")
                .accept(MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON)
                .getCollection(Customer.class);
        list2Json(list, null);
        return NONE;
    }

    @Action(value = "fixedareaAction_findCustomerAssociated2Fixedarea")
    public String findCustomerAssociated2Fixedarea() throws IOException {
        List<Customer> list = (List<Customer>) WebClient.create(
                "http://localhost:8180/bos_crm/webservice/customerService/findCustomerAssociated2Fixedarea")
                .query("id", getModel().getId())
                .accept(MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON).getCollection(Customer.class);
        list2Json(list, null);
        return NONE;
    }

    @Action(value = "fixedareaAction_assignCustomers2Fixedarea", results = {
            @Result(name = "success", location = "/pages/base/fixed_area.html", type = "redirect")})
    public String assignCustomers2Fixedarea() {
        WebClient.create(
                "http://localhost:8180/bos_crm/webservice/customerService/assignCustomers2Fixedarea")
                .accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_XML)
                .query("fixedareaId", getModel().getId()).query("customerIds", customerIds)
                .put(null);
        return SUCCESS;
    }
    
    @Action(value = "fixedareaAction_assignCourier2Fixedarea", results = {
            @Result(name = "success", location = "/pages/base/fixed_area.html", type = "redirect")})
    public String assignCourier2Fixedarea() {
        fixedareaService.assignCourier2Fixedarea(getModel().getId(),courierId,takeTimeId);
        return SUCCESS;
    }
    
    @Action(value = "fixedareaAction_assignSubareas2Fixedarea",results = {
            @Result(name = "success", location = "/pages/base/fixed_area.html", type = "redirect")})
    public String assignSubareas2Fixedarea() {
        fixedareaService.assignSubareas2Fixedarea(getModel().getId(),subareaIds);
        return SUCCESS;
    }
}
