package org.liahnu.bot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.liahnu.bot.mapper.UserMapper;
import org.liahnu.bot.model.domain.User;
import org.liahnu.bot.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author lihanyu
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2025-08-03 19:56:33
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService{

    @Override
    public User queryByQQId(Long qqId) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getQqId, qqId);
        User one = this.getOne(queryWrapper);

        if (one == null) {
            one = new User();
            one.setQqId(qqId);
            one.setNickName("qq用户" + qqId.toString());
            this.save(one);
        }

        return one;
    }

}




