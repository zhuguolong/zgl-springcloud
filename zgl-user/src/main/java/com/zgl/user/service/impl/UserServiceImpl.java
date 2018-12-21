package com.zgl.user.service.impl;

import com.zgl.common.exception.ServiceException;
import com.zgl.user.dao.UserDao;
import com.zgl.user.domain.User;
import com.zgl.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author zhugu
 * 2018/12/21 22:02
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findByPhone(String phone) throws ServiceException {
        if (!phone.matches("^1\\d{10}$")) {
            throw new ServiceException("手机号码填写错误！");
        }
        User user = userDao.findByPhone(phone);
        if (null == user) {
            throw new ServiceException("无用户信息！");
        }
        return user;
    }
}
