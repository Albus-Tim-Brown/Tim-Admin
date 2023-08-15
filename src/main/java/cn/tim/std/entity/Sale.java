package cn.tim.std.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: albus
 * @Date: 2023/8/10 11:03
 * @Project: Tim-Admin-SpringBoot-Project
 * @Package: cn.tim.std.entity
 * @Description:
 */
@ApiModel("销售数据实体")
@TableName("sale")
@Data
public class Sale implements Serializable {

    private static final long serialVersionUID = 8669597962904517734L;

    @ApiModelProperty(value = "id", example = "1")
    @TableId(value = "sale_id", type = IdType.AUTO)
    private Integer saleId;

    @ApiModelProperty(value = "成交订单量", example = "1")
    @TableField("deal_num")
    private Integer dealNum;

    @ApiModelProperty(value = "退款订单量", example = "1")
    @TableField("refund_num")
    private Integer refundNum;

    @ApiModelProperty(value = "浏览量", example = "1")
    @TableField("browse_num")
    private Integer browseNum;

    @ApiModelProperty(value = "加购量", example = "1")
    @TableField("addpurchase_num")
    private Integer addpurchaseNum;

    @ApiModelProperty(value = "预购量", example = "1")
    @TableField("preorder_num")
    private Integer preorderNum;

}
