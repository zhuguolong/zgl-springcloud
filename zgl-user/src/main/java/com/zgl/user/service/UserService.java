package com.zgl.user.service;

import com.zgl.common.exception.ServiceException;
import com.zgl.user.domain.User;

public interface UserService {
    User findByPhone(String phone) throws ServiceException;
}
