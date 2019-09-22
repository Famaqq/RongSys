package com.ruoyi.web.controller.broad;

import com.ruoyi.broad.domain.Area;
import com.ruoyi.broad.domain.BroadMessage;
import com.ruoyi.broad.domain.Tempgroup;
import com.ruoyi.broad.service.IAreaService;
import com.ruoyi.broad.service.IMessageService;
import com.ruoyi.broad.service.ITempgroupService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.page.TableDataInfo;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2019/7/30.
 * @author cx
 */
@Controller
@RequestMapping("/broad/tempgroup")
public class TempgroupController extends BaseController{

    private String prefix = "broad/tempgroup";

    @Autowired
    private ITempgroupService iTempgroupService;

    @Autowired
    private ISysUserService iSysUserService;

    @Autowired
    private IAreaService areaService;

    @Autowired
    private IMessageService messageService;

    @GetMapping()
    public String getTempgroup()
    {
        return prefix + "/tempgroup";
    }
    /*
    * 终端临时列表
    *
    * */
    @PostMapping("/list/{tgname}")
    @ResponseBody
    public TableDataInfo list(@PathVariable("tgname") String tgname,Tempgroup tempgroup)
    {
        startPage();
        List<Tempgroup> list = iTempgroupService.selectAllTempgroup(tgname);
        return getDataTable(list);
    }

    @PostMapping("/list1")
    @ResponseBody
    public TableDataInfo list1(Tempgroup tempgroup)
    {
        startPage();
        List<Tempgroup> list = iTempgroupService.selectTempgroup(tempgroup);
        return getDataTable(list);
    }

    @GetMapping("/add")
    public String addMaintainApply()
    {
        return prefix + "/add";
    }

    @Log(title = "新增临时分组", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Tempgroup tempgroup)
    {
        return toAjax(iTempgroupService.insertTempgroup(tempgroup));
    }

    @PostMapping("/remove")
    @Log(title = "临时分组删除",businessType = BusinessType.DELETE)
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(iTempgroupService.deleteTempgroup(ids));
    }

    @GetMapping("/treeData")
    @ResponseBody
    public List<Map<String,Object>> treedata()
    {
        SysUser currentUser = ShiroUtils.getSysUser();//从session中获取当前登陆用户的userid
        Long userid =  currentUser.getUserId();
        int returnId = new Long(userid).intValue();
        int roleid = iSysUserService.selectRoleid(returnId);//通过所获取的userid去广播用户表中查询用户所属区域的Roleid
//        if(roleid == 1)
//        {
//            List<Map<String, Object>> tree = areaService.selectAreaTree(new Area());
//            return tree;
//        }else{
//            String aid;
//            aid = iSysUserService.selectAid(returnId);//通过所获取的userid去广播用户表中查询用户所属区域的Aid
//            Area update_area = new Area();
//            update_area.setAid(aid);
//            List<Map<String, Object>> tree = areaService.selectAreaTree(update_area);
//            return tree;
//        }
        List<Map<String, Object>> tree = messageService.selectMessageList((new BroadMessage()));
        return tree;

    }
    /**
     * @author cx
     * @param
     *
     * @Description 终端临时列表 详细
     */
    @GetMapping("/detail/{aid}")
    @Log(title = "申请维护记录详细")
    public String detail(@PathVariable("aid") String aid, ModelMap mmp)
    {
        mmp.put("name","tempgroup");
        mmp.put("tempgroup",iTempgroupService.selectTempgroupById(aid));
        return prefix + "/detail";
    }
}
