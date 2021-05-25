package com.jiang.server.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiang.server.utils.RespBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/***
 * 当没有权限时  自定义返回结果
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");

        response.setContentType("application/json");

        PrintWriter out = response.getWriter();

        RespBean bean = RespBean.error("权限不足");
        bean.setCode(403);

        out.write(new ObjectMapper().writeValueAsString(bean));
        out.flush();
        out.close();
    }
}
