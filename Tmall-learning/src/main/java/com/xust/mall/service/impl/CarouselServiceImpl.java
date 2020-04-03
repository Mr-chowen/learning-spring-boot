package com.xust.mall.service.impl;

import com.xust.mall.common.CommonPage;
import com.xust.mall.common.utils.BeanUtil;
import com.xust.mall.common.enums.ServiceResultEnum;
import com.xust.mall.common.utils.PageUtil;
import com.xust.mall.mapper.CarouselMapper;
import com.xust.mall.model.Carousel;
import com.xust.mall.service.CarouselService;
import com.xust.mall.vo.IndexCarouselVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class CarouselServiceImpl implements CarouselService{
    @Autowired
    private CarouselMapper carouselMapper;

    /**
     * 分页处理
     * @param pageUtil
     * @return
     */
    @Override
    public CommonPage listCarousel(PageUtil pageUtil) {
        List<Carousel> carousels = carouselMapper.selectCarousels(pageUtil);
        int total = carouselMapper.getTotalCarousels(pageUtil);
        CommonPage page = new CommonPage(carousels, total, pageUtil.getLimit(), pageUtil.getPage());
        return page;
    }

    /**
     * 保存
     * @param carousel
     * @return
     */
    @Override
    public String saveCarousel(Carousel carousel) {
        if(carouselMapper.insertSelective(carousel)>0){
            return ServiceResultEnum.SUCCESS.getMessage();
        }
        return ServiceResultEnum.DB_ERROR.getMessage();
    }

    /**
     * 修改
     * @param carousel
     * @return
     */
    @Override
    public String editCarousel(Carousel carousel) {
       Carousel temp = carouselMapper.selectByPrimaryKey(carousel.getCarouselId());
       if(temp == null){
           return ServiceResultEnum.DATA_NOT_EXIST.getMessage();
       }
       temp.setCarouselRank(carousel.getCarouselRank());
       temp.setRedirectUrl(carousel.getRedirectUrl());
       temp.setCarouselUrl(carousel.getCarouselUrl());
       temp.setUpdateTime(new Date());
       if(carouselMapper.updateByPrimaryKeySelective(temp)>0){
           return ServiceResultEnum.SUCCESS.getMessage();
       }
        return ServiceResultEnum.DB_ERROR.getMessage();
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public Carousel getCarouselById(Long id) {
        return carouselMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Override
    public Boolean removeBatch(Long[] ids) {
        if(ids.length < 1){
            return false;
        }
        return carouselMapper.deleteBatch(ids) > 0;
    }

    @Override
    public List<IndexCarouselVO> getCarouselForIndex(int number) {
        List<IndexCarouselVO> list = new ArrayList<>();
        List<Carousel> carousels = carouselMapper.selectByCarouselsNum(number);
        if(!CollectionUtils.isEmpty(carousels)){
            list = BeanUtil.copyList(carousels, IndexCarouselVO.class);
        }
        return list;
    }
}
