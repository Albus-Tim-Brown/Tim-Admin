package cn.tim.std.service;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: tim-brown
 * @Date: 2023/8/15 09:26
 * @Project: Tim-Admin-Backend
 * @Package: cn.tim.std.service
 * @Description:
 */
public interface ExcelService {
    void excelWrite(HttpServletResponse response);
}
