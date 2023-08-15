package cn.tim.std.service;

import cn.tim.std.entity.Sale;
import cn.tim.std.utils.Res;

/**
 * @Author: albus
 * @Date: 2023/8/10 11:13
 * @Project: Tim-Admin-SpringBoot-Project
 * @Package: cn.tim.std.service
 * @Description: 销售表统计
 */
public interface SaleService {

    //查询
    Res selectSaleList();

    //修改
    Res updateSale(Sale sale);
}
