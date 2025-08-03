package org.liahnu.bot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.liahnu.bot.model.domain.User;

/**
 * @author lihanyu
 * @description 针对表【user】的数据库操作Service
 * @createDate 2025-08-03 19:56:33
 */
public interface UserService extends IService<User> {

    User queryByQQId(Long qqId);
}
