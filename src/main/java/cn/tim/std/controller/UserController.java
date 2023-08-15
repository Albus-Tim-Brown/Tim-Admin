package cn.tim.std.controller;

import cn.tim.std.entity.User;
import cn.tim.std.service.UserService;
import cn.tim.std.utils.PageUtils;
import cn.tim.std.utils.Res;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: albus
 * @Date: 2023/8/9 16:12
 * @Project: Tim-Admin-SpringBoot-Project
 * @Package: cn.tim.std.controller
 * @Description:
 */
@Api(tags = "用户功能接口")
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 分页模糊查询
     *
     * @param pageUtils 分页组件
     * @param search    查询对象
     * @return
     */
    @ApiOperation("分页模糊查询")
    @GetMapping("selectUserByPageLikeUsername")
    public Res selectUserByPageLikeUsername(@ApiParam("分页模糊查询参数") PageUtils pageUtils, String search) {
        return userService.users(pageUtils, search);
    }

    /*
     * 添加用户
     * */
    @ApiOperation("添加用户")
    @PostMapping("insertUser")
    public Res insertUser(@RequestBody @ApiParam("添加用户参数") User user) {
        return userService.insertUser(user);
    }

    /*
     * 删除用户
     * */
    @ApiOperation("删除用户")
    @DeleteMapping("deleteUser")
    public Res deleteUser(@ApiParam("删除用户参数") Integer userId) {
        return userService.deleteUser(userId);
    }

    /*
     * 更改用户
     * */
    @ApiOperation("更改用户")
    @PutMapping("updateUser")
    public Res updateUser(@RequestBody @ApiParam("更改用户参数") User user) {
        return userService.updateUser(user);
    }
}
