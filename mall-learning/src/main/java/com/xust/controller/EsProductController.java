package com.xust.controller;

import com.xust.common.api.CommonPage;
import com.xust.common.api.CommonResult;
import com.xust.nosql.elasticsearch.document.EsProduct;
import com.xust.service.EsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(tags = "EsProductController",description = "搜索商品管理")
@RequestMapping("/esProduct")
public class EsProductController {
    @Autowired
    private EsProductService esProductService;

    @ApiOperation("导入所有数据库种商品到ES")
    @ResponseBody
    @RequestMapping(value = "/importAll",method = RequestMethod.POST)
    public CommonResult<Integer> inportAllList(){
        int count = esProductService.importAll();
        return CommonResult.success(count);
    }


    @ApiOperation("根据id删除商品")
    @ResponseBody
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public CommonResult<Object> delete(@PathVariable Long id){
        esProductService.delete(id);
        return CommonResult.success(null);
    }

    @ApiOperation("批量删除")
    @ResponseBody
    @RequestMapping(value = "/delete/bath",method = RequestMethod.POST)
    public CommonResult<Object> delete(@RequestParam("ids") List<Long> ids){
        return CommonResult.success(null);
    }

    @ApiOperation("根据id创建商品")
    @ResponseBody
    @RequestMapping(value = "/create/{id}",method = RequestMethod.POST)
    public CommonResult<EsProduct> create(@PathVariable Long id){
        EsProduct esProduct = esProductService.create(id);
        if(esProduct != null){
            return CommonResult.success(esProduct);
        } else {
          return CommonResult.failed();
        }
    }

    @ApiOperation("简单搜索")
    @ResponseBody
    @RequestMapping(value = "/search/simple",method = RequestMethod.GET)
    public CommonResult<CommonPage<EsProduct>> search(@RequestParam(required = false) String keyword,
                                                      @RequestParam(required = false,defaultValue = "0") Integer pageNum,
                                                      @RequestParam(required = false,defaultValue = "5") Integer pageSize){
        Page<EsProduct> esProductPage = esProductService.search(keyword,pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(esProductPage));
    }
}
