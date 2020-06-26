package com.xust.blog.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.xust.blog.entity.User;
import com.xust.blog.mapper.UserMapper;
import com.xust.blog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


}
