package cn.tim.std.controller;

import cn.tim.std.entity.Sale;
import cn.tim.std.service.SaleService;
import cn.tim.std.utils.Res;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: albus
 * @Date: 2023/8/10 11:17
 * @Project: Tim-Admin-SpringBoot-Project
 * @Package: cn.tim.std.controller
 * @Description:
 */
@Api(tags = "销售数据统计接口")
@RestController
@RequestMapping("sale")
public class SaleController {

    @Resource
    private SaleService saleService;


    /**
     * 获取销售数据
     * @return
     */
    @ApiOperation(value = "获取销售数据")
    @GetMapping("")
    public Res selectSaleList() {
        return saleService.selectSaleList();
    }

    /**
     * 修改销售数据
     * @param sale 实体类
     * @return
     */
    @ApiOperation("修改销售数据")
    @PutMapping("")
    public Res updateSale(@RequestBody @ApiParam("修改参数对象") Sale sale){
        return saleService.updateSale(sale);
    }

    @ApiOperation("数据可视化-饼图")
    @GetMapping("saleAllEcharts")
    public Res saleAllEcharts(){
        return saleService.saleAllEcharts();
    }

}
