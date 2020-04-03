package com.xust.mall.controller.admin;


import com.xust.mall.common.enums.ServiceResultEnum;
import com.xust.mall.common.CommonResult;
import com.xust.mall.common.ResultCode;
import com.xust.mall.common.utils.PageUtil;
import com.xust.mall.model.Carousel;
import com.xust.mall.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class AdminCarouselController {
    @Autowired
    private CarouselService carouselService;

    @GetMapping("/carousels")
    public String carousel(HttpServletRequest request){
        request.setAttribute("path","mall_carousel");
        return "admin/mall_carousel";
    }

    /**
     * 分页显示
     * @param params
     * @return
     */
    @GetMapping("/carousels/list")
    @ResponseBody
    public CommonResult listCarousel(@RequestParam Map<String,Object> params){
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultCode.FailResult("请求参数异常！");
        }
        PageUtil pageUtil = new PageUtil(params);
        return ResultCode.SuccessResult(carouselService.listCarousel(pageUtil));
    }

    /**
     * 添加
     * @param carousel
     * @return
     */
    @PostMapping("/carousels/add")
    @ResponseBody
    public CommonResult add(@RequestBody Carousel carousel){
        if(StringUtils.isEmpty(carousel.getCarouselUrl()) || Objects.isNull(carousel.getCarouselRank())){
            return ResultCode.FailResult("输入数据异常，请检查");
        }
        String result = carouselService.saveCarousel(carousel);
        if(ServiceResultEnum.SUCCESS.getMessage().equals(result)){
            return ResultCode.SuccessResult();
        }else{
            return ResultCode.FailResult(result);
        }
    }

    /**
     * 修改
     * @param carousel
     * @return
     */
    @PostMapping("/carousels/update")
    @ResponseBody
    public CommonResult update(@RequestBody Carousel carousel){
        if(Objects.isNull(carousel.getCarouselId())
            || StringUtils.isEmpty(carousel.getCarouselUrl())
            || Objects.isNull(carousel.getCarouselRank())){
            return ResultCode.FailResult("输入数据异常，请检查");
        }
        String result = carouselService.editCarousel(carousel);
        if(ServiceResultEnum.SUCCESS.getMessage().equals(result)){
            return ResultCode.SuccessResult();
        }else {
            return ResultCode.FailResult(result);
        }
    }

    /**
     * 详情
     * @param id
     * @return
     */
    @GetMapping("/carousels/info/{id}")
    @ResponseBody
    public CommonResult info(@PathVariable("id") Long id){
        Carousel carousel = carouselService.getCarouselById(id);
        if(carousel == null){
            return ResultCode.FailResult(ServiceResultEnum.DATA_NOT_EXIST.getMessage());
        }
        return ResultCode.SuccessResult(carousel);
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @PostMapping("/carousels/delete")
    @ResponseBody
    public CommonResult delete(@RequestBody Long ids[]){
        if(ids.length < 1){
            return ResultCode.FailResult("请求参数异常");
        }
        if(carouselService.removeBatch(ids)){
            return ResultCode.SuccessResult();
        }else {
            return ResultCode.FailResult("删除失败");
        }
    }


}
