package com.zgl.user.controller;

import com.zgl.common.exception.ServiceException;
import com.zgl.common.utils.ResultCode;
import com.zgl.common.utils.ResultWrap;
import com.zgl.common.utils.TextConstants;
import com.zgl.user.domain.User;
import com.zgl.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhugu
 * 2018/12/21 22:00
 */
@RestController
@RequestMapping("/v1.0/user")
public class UserController {
    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/findByPhone")
    public Object findByPhone(String phone) {
        try {
            User user = userService.findByPhone(phone);
            return ResultWrap.ok("查询成功！", user);
        } catch (ServiceException e) {
            e.printStackTrace();
            return ResultWrap.err(log, ResultCode.ERROR_GLOBAL, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultWrap.err(log, ResultCode.ERROR_GLOBAL, TextConstants.SERVER_EXCEPTION);
        }
    }
}
