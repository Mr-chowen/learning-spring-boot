package com.xust.controller;

import com.xust.common.util.CommonResult;
import com.xust.model.Position;
import com.xust.service.PositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/position")
@Api(tags = "PositionController",description = "职位管理")
public class PositionController {
    @Autowired
    PositionService positionService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PositionController.class);

    @ApiOperation("获取所有职位信息")
    @GetMapping("/")
    @ResponseBody
    public List<Position> getAllPositions(){
        return positionService.getAllPositions();
    }

    @ApiOperation("添加职位信息")
    @PostMapping("/")
    @ResponseBody
    public CommonResult addPosition(@RequestBody Position position){
        if(positionService.addPosition(position)==1){
            return CommonResult.ok("添加成功！");
        }
        return CommonResult.error("添加失败！");
    }

    @ApiOperation("更新职位信息")
    @PutMapping("/")
    @ResponseBody
    public CommonResult updatePositions(@RequestBody Position position){
        if(positionService.updatePosition(position)==1){
            return CommonResult.ok("更新成功！");
        }
        return  CommonResult.error("更新失败！");
    }

    @ApiOperation("根据id删除职位信息")
    @DeleteMapping("/{id}")
    @ResponseBody
    public CommonResult deletePositionById(@PathVariable Integer id){
        if(positionService.deletePositionById(id)==1){
            return CommonResult.ok("删除成功！");
        }
        return CommonResult.error("删除失败！");
    }

    @ApiOperation("批量删除职位信息")
    @DeleteMapping("/")
    @ResponseBody
    public CommonResult deletePositionByIds(Integer[] ids){
        if(positionService.deletePositionByIds(ids) == ids.length){
            return CommonResult.error("删除成功！");
        }
        return CommonResult.error("删除失败！");
    }


}
