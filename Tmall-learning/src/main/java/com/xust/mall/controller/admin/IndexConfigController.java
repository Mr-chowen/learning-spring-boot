package com.xust.mall.controller.admin;

import com.xust.mall.common.CommonResult;
import com.xust.mall.common.ResultCode;
import com.xust.mall.common.enums.IndexConfigTypeEnum;
import com.xust.mall.common.enums.ServiceResultEnum;
import com.xust.mall.common.utils.PageUtil;
import com.xust.mall.model.IndexConfig;
import com.xust.mall.service.IndexConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class IndexConfigController {
    @Autowired
    private IndexConfigService indexConfigService;

    @GetMapping("/indexConfigs")
    public String indexConfigPage(HttpServletRequest request,@RequestParam("configType") int configType){
        IndexConfigTypeEnum indexConfigTypeEnum = IndexConfigTypeEnum.getIndexConfigTypeEnumByType(configType);
        if(indexConfigTypeEnum.equals(IndexConfigTypeEnum.DEFAULT)){
            return "error/error_5xx";
        }
        request.setAttribute("path",indexConfigTypeEnum.getName());
        request.setAttribute("configType",configType);
        return "admin/mall_index_config";
    }

    @GetMapping("/indexConfigs/list")
    @ResponseBody
    public CommonResult list(@RequestParam Map<String,Object> params){
        if(StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))){
            return ResultCode.FailResult("请求参数异常");
        }
        PageUtil pageUtil = new PageUtil(params);
        return ResultCode.SuccessResult(indexConfigService.getConfigPage(pageUtil));
    }

    @PostMapping("/indexConfigs/save")
    @ResponseBody
    public CommonResult save(@RequestBody IndexConfig indexConfig){
        String result = indexConfigService.saveIndexConfig(indexConfig);
        if(ServiceResultEnum.SUCCESS.getMessage().equals(result)){
            return ResultCode.SuccessResult();
        } else{
            return ResultCode.FailResult(result);
        }
    }

    @PostMapping("/indexConfigs/update")
    @ResponseBody
    public CommonResult update(@RequestBody IndexConfig indexConfig){
        if(Objects.isNull(indexConfig.getConfigType())
                || StringUtils.isEmpty(indexConfig.getConfigName())
                || Objects.isNull(indexConfig.getConfigRank())){
            return ResultCode.FailResult("请求参数异常");
        }
        String result = indexConfigService.updateIndexConfig(indexConfig);
        if(ServiceResultEnum.SUCCESS.getMessage().equals(result)){
            return ResultCode.SuccessResult();
        } else{
            return ResultCode.FailResult(result);
        }
    }

    @GetMapping("/indexConfigs/info/{id}")
    @ResponseBody
    public CommonResult info(@PathVariable("id") Long id){
        IndexConfig indexConfig = indexConfigService.getIndexConfig(id);
        if(indexConfig == null){
            return ResultCode.FailResult("未查询到相关信息");
        }
        return ResultCode.SuccessResult(indexConfig);
    }

    @DeleteMapping("/indexConfigs/delete")
    @ResponseBody
    public CommonResult delete(@RequestBody Long[] ids){
        if(ids.length < 1){
            return ResultCode.FailResult("请求参数异常");
        }
        if(indexConfigService.deleteBatch(ids)){
            return ResultCode.SuccessResult();
        }else{
            return ResultCode.FailResult("删除失败");
        }
    }
}
