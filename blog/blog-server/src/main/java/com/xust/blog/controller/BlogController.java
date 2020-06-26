package com.xust.blog.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xust.blog.common.CommonResult;
import com.xust.blog.entity.Blog;
import com.xust.blog.service.BlogService;
import com.xust.blog.utils.ShiroUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhouven
 * @since 2020-06-12
 */
@Api(tags = "BlogController",description = "博客管理")
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    BlogService blogService;

    @ApiOperation("分页显示")
    @GetMapping("/list")
    public CommonResult list(@RequestParam(defaultValue = "1") Integer currentPage){
        Page page = new Page(currentPage,5);
        IPage pageData = blogService.page(page,new QueryWrapper<Blog>().orderByDesc("created"));
        return CommonResult.success(pageData);
    }

    @ApiOperation("博客详情")
    @GetMapping("/{id}")
    public CommonResult detail(@PathVariable(name="id") Long id){
        Blog blog = blogService.getById(id);
        Assert.notNull(blog,"该博客已被删除");
        return CommonResult.success(blog);
    }

    @ApiOperation("编辑博客")
    @PostMapping("/edit")
    @RequiresAuthentication
    public CommonResult edit(@Validated @RequestBody Blog blog){

        Blog temp = null;
        if(blog.getId() != null){
            temp = blogService.getById(blog.getId());
            Assert.isTrue(temp.getUserId().longValue() == ShiroUtil.getProfile().getId().longValue(),"没有权限编辑该博客");
        } else {
            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }

        BeanUtil.copyProperties(blog,temp,"id","userId","created","status");
        blogService.saveOrUpdate(temp);

        return CommonResult.success(null);
    }

}
