package com.xust.controller;

import com.xust.common.api.CommonPage;
import com.xust.common.api.CommonResult;
import com.xust.mbg.model.PmsBrand;
import com.xust.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 品牌管理
 */
@Controller
@RequestMapping("/brand")
@Api(tags = "PmsBrandController",description = "商品品牌管理")
public class PmsBrandController {
    private static final Logger LOGGER= LoggerFactory.getLogger(PmsBrandController.class);

    @Autowired
    private PmsBrandService pmsBrandService;


    @PreAuthorize("hasAuthority('pms:brand:read')")
    @ResponseBody
    @ApiOperation("获取品牌列表")
    @RequestMapping(value = "/listAll",method = RequestMethod.GET)
    public CommonResult<List<PmsBrand>> getBrandList(){
        return CommonResult.success(pmsBrandService.listAllBrand());
    }

    @PreAuthorize("hasAuthority('pms:brand:create')")
    @ResponseBody
    @ApiOperation("添加品牌")
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public CommonResult createBrand(@RequestBody PmsBrand pmsBrand){
        CommonResult commonResult;
        int count = pmsBrandService.createBrand(pmsBrand);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrand);
            LOGGER.debug("createBrand success:{}",pmsBrand);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("createBrand failed:{}",pmsBrand);
        }
        return commonResult;
    }

    @PreAuthorize("hasAuthority('pms:brand:update')")
    @ResponseBody
    @ApiOperation("更新指定ID品牌信息")
    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    public CommonResult updateBrand(@PathVariable("id") Long id, PmsBrand pmsBrandDto, BindingResult result){
        CommonResult commonResult;
        int count = pmsBrandService.updateBrand(id,pmsBrandDto);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrandDto);
            LOGGER.debug("updateBrand success:{}",pmsBrandDto);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("updateBrand failed:{}",pmsBrandDto);
        }
        return commonResult;
    }

    @PreAuthorize("hasAuthority('pms:brand:delete')")
    @ResponseBody
    @ApiOperation("删除指定ID品牌信息")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public CommonResult deleteBrand(@PathVariable("id") Long id){
        int count = pmsBrandService.deleteBrand(id);
        if (count == 1) {
            LOGGER.debug("deleteBrand success:{}",id);
            return CommonResult.success(null);
        } else {
            LOGGER.debug("deleteBrand failed:{}", id);
            return CommonResult.failed("操作失败");
        }
    }

    @PreAuthorize("hasAuthority('pms:brand:read')")
    @ResponseBody
    @ApiOperation("分页查询品牌列表")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize",defaultValue = "3") Integer pageSize){
        List<PmsBrand> brandList = pmsBrandService.listBrand(pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(brandList));
    }

    @PreAuthorize("hasAuthority('pms:brand:read')")
    @ResponseBody
    @ApiOperation("获取指定ID品牌信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public CommonResult<PmsBrand> brand(@PathVariable("id") Long id){
        return CommonResult.success(pmsBrandService.getBrand(id));
    }
}
