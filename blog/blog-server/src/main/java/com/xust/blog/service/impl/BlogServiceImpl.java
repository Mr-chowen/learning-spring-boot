package com.xust.blog.service.impl;

import com.xust.blog.entity.Blog;
import com.xust.blog.mapper.BlogMapper;
import com.xust.blog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhouven
 * @since 2020-06-12
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
