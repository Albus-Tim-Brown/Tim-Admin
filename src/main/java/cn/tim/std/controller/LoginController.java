package cn.tim.std.controller;

import cn.tim.std.entity.User;
import cn.tim.std.mapper.UserMapper;
import cn.tim.std.utils.Res;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: albus
 * @Date: 2023/8/8 10:03
 * @Project: Tim-Admin-SpringBoot-Project
 * @Package: cn.tim.std.controller
 * @Description: RestFul风格
 * 文档交换 整合swagger 产生文本交互
 */
@Api(tags = "登录注册接口")
@RestController
public class LoginController {

    @Resource
    private UserMapper userMapper;

    /*
     * 登录
     * */
    @ApiOperation("用户登录")
    @PostMapping("login")
    public Res<User> login(@RequestBody @ApiParam("用户登录参数对象") User user) {
        Subject subject = SecurityUtils.getSubject();
        System.out.println("controller-subject: " + subject);
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        System.out.println("controller-token: " + token);
        subject.login(token);//实际上去realm中进行验证，验证会抛出异常，由全局异常拦截处理
        return Res.ok("登录成功!");
    }
    //    @RequestMapping("login")
//    public Res<User> login(User user) {
//        User userRes = userMapper.selectOne(new QueryWrapper<User>().eq("username", user.getUsername()));
//        if (userRes == null) {
//            return Res.error("用户名不存在!");
//        }
//        if (userRes.getPassword().equals(user.getPassword())) {
//            return Res.ok("登录成功!");
//        } else {
//            return Res.error("登录失败!");
//        }
//    }

    /*
     * 登出
     * */
    @ApiOperation("用户登出")
    @GetMapping("loginOut")
    public Res<User> loginOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Res.ok("退出成功!");
    }

    /*
     * 用户注册
     * */
    @ApiOperation("用户注册")
    @PostMapping("register")
    public Res<User> register(@RequestBody @ApiParam("用户注册参数") User user) {
        Map<String, String> userMap = new HashMap<>();
        userMap.put("username", user.getUsername());
        userMap.put("password", user.getPassword());
//        User selectOne = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, userMap.get("username")));
        User selectOne1 = userMapper.selectOne(new QueryWrapper<User>().eq("username", userMap.get("username")));
        if (selectOne1 != null) {
            return Res.error("用户已存在!");
        }
        User addUser = new User();
        addUser.setUsername(userMap.get("username"));
        addUser.setPassword(userMap.get("password"));
        return userMapper.insert(addUser) > 0 ?
                Res.ok("成功注册!") :
                Res.error("注册失败!");
    }

    /*
     * 修改用户名和密码
     * */
    @PostMapping("reset")
    public Res<User> reset(@RequestBody @ApiParam("用户修改用户名密码参数") User user) {
        Map<String, String> userMap1 = new HashMap<>();
        userMap1.put("username", user.getUsername());
        userMap1.put("password", user.getPassword());
        User selectOne = userMapper.selectOne(new QueryWrapper<User>().eq("username", userMap1.get("username")));
        if (selectOne == null) {
            return Res.error("用户不存在!");
        }
        return userMapper.updateById(user) > 0 ?
                Res.ok("修改成功!") :
                Res.error("修改失败!");
    }
//
//    /*
//     * 删除用户
//     * */
//    @PostMapping("delete")
//    public Res<User> delete(@RequestBody User user) {
//        Map<String, String> userMap1 = new HashMap<>();
//        userMap1.put("username", user.getUsername());
//        User selectOne = userMapper.selectOne(new QueryWrapper<User>().eq("username", userMap1.get("username")));
//        if (selectOne == null) {
//            return Res.error("用户不存在!");
//        }
//        return userMapper.deleteById(user) > 0 ?
//                Res.ok("删除成功!") :
//                Res.error("删除失败!");
//    }
//
//    /*
//     * 查询所有用户
//     * */
//    @GetMapping("findAll")
//    public Res<List<User>> findAll() {
//        return Res.ok(userMapper.selectList(null));
//    }
//
//    /*
//     * 根据名字查询用户
//     * */
//    @GetMapping("findByName")
//    public Res<List<User>> findByName(String username) {
//        return Res.ok(userMapper.selectList(new QueryWrapper<User>().eq("username", username)));
//    }
//
//    /*
//     * 模糊查询
//     * */
//    @GetMapping("findLike")
//    public Res<List<User>> findLike(String username) {
//        return Res.ok(userMapper.selectList(new QueryWrapper<User>().like("username", username)));
//    }
}
