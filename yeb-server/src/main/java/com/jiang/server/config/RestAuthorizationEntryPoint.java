package com.jiang.server.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiang.server.utils.RespBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/***
 * 当为登录或者token失效时访问接口时  定义返回结果
 */
@Component
public class RestAuthorizationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");

        response.setContentType("application/json");

        PrintWriter out = response.getWriter();

        RespBean bean = RespBean.error("未登录");
        bean.setCode(401);

        out.write(new ObjectMapper().writeValueAsString(bean));
        out.flush();
        out.close();
    }
}
