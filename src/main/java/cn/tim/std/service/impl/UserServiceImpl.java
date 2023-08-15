package cn.tim.std.service.impl;

import cn.tim.std.entity.User;
import cn.tim.std.mapper.UserMapper;
import cn.tim.std.service.UserService;
import cn.tim.std.utils.PageUtils;
import cn.tim.std.utils.Res;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @Author: albus
 * @Date: 2023/8/9 16:01
 * @Project: Tim-Admin-SpringBoot-Project
 * @Package: cn.tim.std.service.impl
 * @Description: UserService实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Res users(PageUtils pageUtils, String search) {
        Page<User> userPage = userMapper.selectPage(
                new Page<>(pageUtils.getCurrent(), pageUtils.getRows()),
                search != null && search != "" ?
                        new QueryWrapper<User>().like("username", search) : null);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", userPage.getTotal());
        map.put("list",userPage.getRecords());
        map.put("pages",userPage.getPages());
        return Res.ok(map);
    }

    @Override
    public Res insertUser(@RequestBody User user) {
        return userMapper.insert(user) > 0 ?
                Res.ok("添加成功!") :
                Res.error("添加失败!");
    }

    @Override
    public Res deleteUser(Integer userId) {
        return userMapper.deleteById(userId) > 0 ?
                Res.ok("删除成功!") :
                Res.error("删除失败!");
    }

    @Override
    public Res updateUser(@RequestBody User user) {
        return userMapper.updateById(user) > 0 ?
                Res.ok("修改成功!") :
                Res.error("修改失败!");
    }
}
