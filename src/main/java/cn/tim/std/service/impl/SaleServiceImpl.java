package cn.tim.std.service.impl;

import cn.tim.std.entity.Sale;
import cn.tim.std.entity.SaleVo;
import cn.tim.std.mapper.SaleMapper;
import cn.tim.std.service.SaleService;
import cn.tim.std.utils.Res;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Res saleAllEcharts() {
        List<Sale> sales = saleMapper.selectList(null);
        System.out.println(sales);
        ArrayList<SaleVo> saleRes = new ArrayList<>();
        SaleVo saleVo1 = new SaleVo(sales.get(0).getDealNum(),"成交订单量");
        SaleVo saleVo2 = new SaleVo(sales.get(0).getRefundNum(),"退款订单量");
        SaleVo saleVo3 = new SaleVo(sales.get(0).getBrowseNum(),"浏览量");
        SaleVo saleVo4 = new SaleVo(sales.get(0).getAddpurchaseNum(),"加购量");
        SaleVo saleVo5 = new SaleVo(sales.get(0).getPreorderNum(),"预购量");
        saleRes.add(saleVo1);
        saleRes.add(saleVo2);
        saleRes.add(saleVo3);
        saleRes.add(saleVo4);
        saleRes.add(saleVo5);
        return Res.ok(saleRes);
    }
}
