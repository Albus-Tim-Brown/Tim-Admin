package cn.tim.std.service.impl;

import cn.tim.std.entity.User;
import cn.tim.std.mapper.UserMapper;
import cn.tim.std.service.ExcelService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Author: tim-brown
 * @Date: 2023/8/15 09:27
 * @Project: Tim-Admin-Backend
 * @Package: cn.tim.std.service.impl
 * @Description:
 */
@Service
public class ExcelServiceImpl implements ExcelService {

    @Resource
    private UserMapper userMapper;

    @Override
    public void excelWrite(HttpServletResponse response) throws RuntimeException {
        //1.创建输出流
        OutputStream outputStream = null;
        try {
            //2.创建HSSFWorkbook工作表对象
            HSSFWorkbook wb = new HSSFWorkbook();
            //创建工作表
            HSSFSheet sheet = wb.createSheet();
            HSSFRow row = sheet.createRow(0);
            row.createCell(0).setCellValue("用户名ID");
            row.createCell(1).setCellValue("用户名");
            row.createCell(2).setCellValue("密码");
            //写入数据
            // -查询数据库获取所有数据
            List<User> users = userMapper.selectList(null);
            //遍历数据
            for (int i = 1; i <= users.size(); i++) {
                HSSFRow rowData = sheet.createRow(i); //创建第i行的数据
                rowData.createCell(0).setCellValue(users.get(i - 1).getUserId());
                rowData.createCell(1).setCellValue(users.get(i - 1).getUsername());
                rowData.createCell(2).setCellValue(users.get(i - 1).getPassword());
            }
            //输出Excel文件页面
            // -文件名称转码
            String fileName = URLEncoder.encode("用户信息表", "utf-8");
            response.reset();
            //一系列跨域问题
            /*通过当前请求头，发送当前Excel格式内容。请求浏览器响应下载*/
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
            /*不需要验证直接放行Content-Disposition*/
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition,filename");
            /*解决Excel导出的跨域问题*/
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type");
            // -设置响应的ContentType
            response.setContentType("application/vnd.ms-excel");
            // -设置响应字符集，解决中文乱码问题
            response.setCharacterEncoding("utf-8");
            // -通过outputStream流写出Excel
            outputStream = response.getOutputStream();
            wb.write(outputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
