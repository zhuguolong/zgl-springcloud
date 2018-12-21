package com.zgl.user.dao;

import com.zgl.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    User findByPhone(String phone);
}
