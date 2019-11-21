package com.fff.oauthserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fff.oauthserver.mapper.UserMapper;
import com.fff.oauthserver.model.User;
import com.fff.oauthserver.service.IUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现类
 * @author lihaodong
 * @since 2019-03-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User findByUserName(String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 查询条件
        queryWrapper.lambda().eq(User::getUsername, name);
        return baseMapper.selectOne(queryWrapper);
    }

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User create(String username, String password) {

        User user = new User();
        user.setUsername(username);
        password = "{bcrypt}" + passwordEncoder.encode(password);
        user.setPassword(password);
        int insert = baseMapper.insert(user);
        return user;
    }

}
