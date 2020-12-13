package com.wuwei.dao;

import com.wuwei.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wuwei
 * @date 2018/7/7 17:16
 * @description
 */
@Repository
public interface UserMapper {
    int saveUser(User user);

    List<User> findAllUser();
}
