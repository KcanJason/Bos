package com.itheima.bos.web.action.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.apache.struts2.convention.annotation.Result;

import com.itheima.bos.domain.base.Area;
import com.itheima.bos.service.base.AreaService;
import com.itheima.utils.PinYin4jUtils;

/**  
 * ClassName:AreaAction <br/>  
 * Function:  <br/>  
 * Date:     Nov 3, 2017 9:22:02 AM <br/>       
 */
@Controller
@Namespace("/")
@Scope("prototype")
@ParentPackage("struts-default")
public class AreaAction extends BaseAction<Area> {

    private static final long serialVersionUID = 1L;
    private File file;
    
    @Autowired
    private AreaService areaService;
    private Integer page;
    private Integer rows;
    private String q;
    
    @Action(value = "areaAction_importXLS", results = {@Result(name = "success", location = "/pages/base/area.html", type = "redirect")})
    public String importXLS() throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));
        List<Area> list = new ArrayList<>();
        // 获取到第一个sheet
        HSSFSheet sheet = workbook.getSheetAt(0);
        // 获取到每一行
        for (Row row : sheet) {
            //第一行不要
            if(row.getRowNum() == 0) {
                continue;
            }
            String province = row.getCell(1).getStringCellValue();  // 获取省
            String city = row.getCell(2).getStringCellValue();      // 获取市
            String district = row.getCell(3).getStringCellValue();  // 获取区域
            String postcode = row.getCell(4).getStringCellValue();  // 获取邮编
            // 去掉省市区的最后一个字
            province = province.substring(0,province.length()-1);
            city = city.substring(0,city.length()-1);
            district = district.substring(0,district.length()-1);
            // 获取城市编码
            String citycode = PinYin4jUtils.hanziToPinyin(city,"").toUpperCase();
            // 获取简码
            String[] headByString = PinYin4jUtils.getHeadByString(province+city+district, true);
            System.out.println(headByString.toString());
            // 拼接简码
            String shortcode = StringUtils.join(headByString);
            Area area = new Area(province,city,district,postcode,citycode,shortcode);
            list.add(area);
        }
        areaService.save(list);
        workbook.close();
        return SUCCESS;
    }

    @Action("areaAction_findByPage")
    public String findByPage() throws IOException {
        Pageable pageable = new PageRequest(page -1, rows);
        Page<Area> page = areaService.findByPage(pageable);
        page2Json(page, new String[]{"subareas"});
        return NONE;
    }
    
    @Action("areaAction_findAll")
    public String findAll() throws IOException {
        List<Area> list;
        if(StringUtils.isNotEmpty(q)) {
            list = areaService.findByQ(q);
        }else {
            list = areaService.findAll();
        }
        list2Json(list, new String[] {"subareas"});
        return  NONE;
    }
    
    public void setFile(File file) {
        this.file = file;
    }

    public void setAreaService(AreaService areaService) {
        this.areaService = areaService;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public void setQ(String q) {
        this.q = q;
    }
    
    
}
  
