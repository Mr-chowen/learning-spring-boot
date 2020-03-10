package com.xust.controller;

import com.xust.common.util.CommonResult;
import com.xust.model.JobLevel;
import com.xust.service.JobLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "JobLevelController",description = "职级管理")
@RestController
@RequestMapping("/system/level")
public class JobLevelController {
    @Autowired
    JobLevelService jobLevelService;

    @ApiOperation("获取职级信息")
    @GetMapping("/")
    @ResponseBody
    public List<JobLevel> getAllJobLevels(){
        return jobLevelService.getAllJobLevels();
    }

    @ApiOperation("添加职级信息")
    @PostMapping("/")
    @ResponseBody
    public CommonResult addJobLevel(@RequestBody JobLevel jobLevel){
        if(jobLevelService.addJobLevel(jobLevel) ==1){
            return CommonResult.ok("添加成功！");
        }
        return CommonResult.error("添加失败！");
    }

    @ApiOperation("更新职级信息")
    @PutMapping("/")
    @ResponseBody
    public CommonResult updateJobLevelById(@RequestBody JobLevel jobLevel){
        if(jobLevelService.updateJobLevelById(jobLevel) == 1){
            return CommonResult.ok("更新成功！");
        }
        return CommonResult.error("更新失败！");
    }

    @ApiOperation("根据id删除职级信息")
    @DeleteMapping("/{id}")
    @ResponseBody
    public CommonResult deleteJobLevelById(@PathVariable Integer id){
        if(jobLevelService.deleteJobLevelById(id) == 1){
            return CommonResult.ok("删除成功！");
        }
        return CommonResult.error("删除失败！");
    }

    @ApiOperation("批量删除职级")
    @DeleteMapping("/")
    @ResponseBody
    public CommonResult deleteJobLevelById(Integer[] ids){
        if(jobLevelService.deleteJobLevelsByIds(ids) == ids.length){
            return CommonResult.ok("删除成功！");
        }
        return CommonResult.error("删除失败！");
    }
}
