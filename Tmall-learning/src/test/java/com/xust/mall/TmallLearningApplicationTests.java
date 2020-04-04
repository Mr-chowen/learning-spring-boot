package com.xust.mall;

import com.xust.mall.common.utils.BeanUtil;
import com.xust.mall.common.utils.MD5Util;
import com.xust.mall.mapper.AdminUserMapper;
import com.xust.mall.mapper.GoodsMapper;
import com.xust.mall.mapper.IndexConfigMapper;
import com.xust.mall.model.AdminUser;
import com.xust.mall.model.Goods;
import com.xust.mall.model.IndexConfig;
import com.xust.mall.service.AdminUserService;
import com.xust.mall.vo.IndexGoodsConfigVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TmallLearningApplicationTests {


    @Test
    public void contextLoads() {


    }

}
