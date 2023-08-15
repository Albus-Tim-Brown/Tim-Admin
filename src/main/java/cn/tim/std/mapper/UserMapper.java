package cn.tim.std.mapper;

import cn.tim.std.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: albus
 * @Date: 2023/8/8 09:36
 * @Project: Tim-Admin-SpringBoot-Project
 * @Package: cn.tim.std.mapper
 * @Description:
 */
@Repository     //可加可不加
public interface UserMapper extends BaseMapper<User> {
}
