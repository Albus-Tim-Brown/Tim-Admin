package cn.tim.std.service;

import cn.tim.std.entity.User;
import cn.tim.std.utils.PageUtils;
import cn.tim.std.utils.Res;

import java.util.List;

/**
 * @Author: albus
 * @Date: 2023/8/8 10:03
 * @Project: Tim-Admin-SpringBoot-Project
 * @Package: cn.tim.std.service
 * @Description:
 */
public interface UserService {

    //分页模糊查询
    Res users(PageUtils pageUtils, String search);
    //增
    Res insertUser(User user);
    //删 ById
    Res deleteUser(Integer userId);
    //改
    Res updateUser(User user);
}
