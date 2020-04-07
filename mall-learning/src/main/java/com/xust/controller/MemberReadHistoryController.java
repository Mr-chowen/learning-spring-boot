package com.xust.controller;

import com.xust.common.api.CommonResult;
import com.xust.nosql.mongodb.document.MemberReadHistory;
import com.xust.service.MemberReadHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "MemberReadHistoryController")
@Controller
@RequestMapping("/member/readHistory")
public class MemberReadHistoryController {
    @Autowired
    private MemberReadHistoryService memberReadHistoryService;

    @ApiOperation("创建浏览记录")
    @ResponseBody
    @RequestMapping(value = "create",method = RequestMethod.POST)
    public CommonResult create(@RequestBody MemberReadHistory memberReadHistory){
        int count = memberReadHistoryService.create(memberReadHistory);
        if(count > 0){
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("删除浏览记录")
    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public CommonResult delete(@RequestParam("ids") List<String> ids){
        int count = memberReadHistoryService.delete(ids);
        if(count > 0){
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("显示浏览记录")
    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public CommonResult<List<MemberReadHistory>> list(Long memeberId){
        List<MemberReadHistory> memberReadHistoryList = memberReadHistoryService.list(memeberId);
        return CommonResult.success(memberReadHistoryList);
    }

}
