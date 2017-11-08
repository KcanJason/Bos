package com.itheima.bos.web.action.fore;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.crm.Customer;
import com.itheima.utils.SmsUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ClassName:CustomerAction <br/>
 * Function: <br/>
 * Date: Nov 7, 2017 3:07:21 PM <br/>
 */
@Controller
@Namespace("/")
@Scope("prototype")
@ParentPackage("struts-default")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

    private static final long serialVersionUID = 1L;
    private Customer model = new Customer();
    private String checkcode;

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    @Override
    public Customer getModel() {
        return model;
    }

    @Action("customerAction_sendSMS")
    public String sendSMS() {
        // 生成的验证码
        String code = RandomStringUtils.randomNumeric(4);
        ServletActionContext.getRequest().getSession().setAttribute(model.getTelephone(), code);
        String msg = "尊敬的客户你好，您本次获取的验证码为:" + code;
        System.out.println(code);
        SmsUtils.sendSmsByWebService(model.getTelephone(), msg);
        return NONE;
    }

    @Action(value = "customerAction_regist",
            results = {
                    @Result(name = "success", location = "/signup-success.html", type = "redirect"),
                    @Result(name = "error", location = "/signup-fail.html", type = "redirect")})
    public String regist() {
        String serverCode = (String) ServletActionContext.getRequest().getSession()
                .getAttribute(model.getTelephone());
        if (StringUtils.isNotEmpty(serverCode) && StringUtils.isNotEmpty(checkcode)
                && serverCode.equals(checkcode)) {
            WebClient.create("http://localhost:8180/bos_crm/webservice/customerService/customer")
                    .type(MediaType.APPLICATION_JSON).post(model);
            return SUCCESS;
        }
        return ERROR;
    }
}
