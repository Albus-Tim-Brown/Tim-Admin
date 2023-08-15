package cn.tim.std.realm;

import cn.tim.std.entity.User;
import cn.tim.std.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * @Author: albus
 * @Date: 2023/8/8 11:00
 * @Project: Tim-Admin-SpringBoot-Project
 * @Package: cn.tim.std.realm
 * @Description: User领域
 * 认证： 刷身份证
 * 授权： 鉴定权限，root，VIP等
 */
public class UserRealm extends AuthorizingRealm {

    @Resource
    private UserMapper userMapper;
    @Override   //授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("----------授权----------");
        return null;
    }

    @Override   //认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("----------认证----------");
        //1.获取用户名和密码的token信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
//        System.out.println("realm-username" + token.getUsername());
//        System.out.println("realm-password" + String.valueOf(token.getPassword()));
        //2.根据token中封装的user信息获取用户名和密码，且查询数据库，拿到安全的真实数据
        User user = userMapper.selectOne(new QueryWrapper<User>()
                .eq("username", token.getUsername()));
        //3.判断用户名是否存在
        if (user == null){
            throw new UnknownAccountException("用户不存在");//shiro中用户名不存在的异常
        }
        if (!user.getPassword().equals(String.valueOf(token.getPassword()))){
            throw new IncorrectCredentialsException("密码不正确");//shiro中密码不对的异常
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
    }
}
