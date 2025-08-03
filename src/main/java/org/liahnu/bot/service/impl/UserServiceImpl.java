package org.liahnu.bot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.liahnu.bot.mapper.UserMapper;
import org.liahnu.bot.model.domain.User;
import org.liahnu.bot.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User getByQQId(Long qqId) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getQQId, qqId);
        User one = this.getOne(queryWrapper);

        if (one == null) {
            one = new User();
            one.setQQId(qqId);
            one.setNickname(qqId.toString());
            this.save(one);
        }

        return one;
    }

}
