package com.fff.oauthserver.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fff.oauthserver.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lihaodong
 * @since 2019-03-14
 */
public interface UserMapper extends BaseMapper<User> {

    @Override
    User selectOne(@Param("ew") Wrapper<User> wrapper);

    @Override
    int insert(User user);
}
