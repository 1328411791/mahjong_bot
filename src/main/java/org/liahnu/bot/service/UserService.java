package org.liahnu.bot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.liahnu.bot.model.domain.User;

public interface UserService extends IService<User> {

    User getByQQId(Long qqId);
}
