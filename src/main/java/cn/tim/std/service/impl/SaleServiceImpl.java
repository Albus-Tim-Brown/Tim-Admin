package cn.tim.std.service.impl;

import cn.tim.std.entity.Sale;
import cn.tim.std.mapper.SaleMapper;
import cn.tim.std.service.SaleService;
import cn.tim.std.utils.Res;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: albus
 * @Date: 2023/8/10 11:15
 * @Project: Tim-Admin-SpringBoot-Project
 * @Package: cn.tim.std.service.impl
 * @Description:
 */
@Service
public class SaleServiceImpl implements SaleService {

    @Resource
    private SaleMapper saleMapper;

    @Override
    public Res selectSaleList() {
        return Res.ok(saleMapper.selectList(null));
    }

    @Override
    public Res updateSale(Sale sale) {
        return saleMapper.updateById(sale) > 0 ?
                Res.ok("修改成功") :
                Res.error("修改失败");
    }
}
