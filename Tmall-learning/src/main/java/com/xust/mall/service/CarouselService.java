package com.xust.mall.service;

import com.xust.mall.common.CommonPage;
import com.xust.mall.common.utils.PageUtil;
import com.xust.mall.model.Carousel;
import com.xust.mall.vo.IndexCarouselVO;
import java.util.List;

public interface CarouselService {
    CommonPage listCarousel(PageUtil pageUtil);

    String saveCarousel(Carousel carousel);

    String editCarousel(Carousel carousel);

    Carousel getCarouselById(Long id);

    Boolean removeBatch(Long ids[]);

    List<IndexCarouselVO> getCarouselForIndex(int number);

}
