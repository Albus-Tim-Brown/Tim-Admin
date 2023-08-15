package cn.tim.std.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: albus
 * @Date: 2023/8/8 09:33
 * @Project: Tim-Admin-SpringBoot-Project
 * @Package: cn.tim.std.entity
 * @Description:
 */
@ApiModel("用户实体")   //标注实体类说明
@TableName("user")
@Data
public class User {

    @ApiModelProperty(value = "用户id" , example = "1") //Integer类型需要加example = "1"
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty(value = "用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;
}
