package cn.tim.std.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: tim-brown
 * @Date: 2023/8/15 16:48
 * @Project: Tim-Admin-Backend
 * @Package: cn.tim.std.entity
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class SaleVo {
    private Integer value;
    private String name;
}
