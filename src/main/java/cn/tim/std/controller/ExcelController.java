package cn.tim.std.controller;

import cn.tim.std.service.ExcelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: tim-brown
 * @Date: 2023/8/15 09:24
 * @Project: Tim-Admin-Backend
 * @Package: cn.tim.std.controller
 * @Description:
 */
@Api(tags = "导出Excel接口")
@RestController
@RequestMapping("excel")
public class ExcelController {

    @Resource
    private ExcelService excelService;

    @ApiOperation("excel导出")
    @GetMapping("excelWrite")
    public void excelWriter(HttpServletResponse response){
        excelService.excelWrite(response);
    }

}
