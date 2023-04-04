package com.example.attendancedemo.filter;

import com.example.attendancedemo.common.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    //创建路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        //获取本次请求的url
        String requestURI = req.getRequestURI();
        log.info("拦截到请求{}",requestURI);
        //定义不需要处理的请求路径
        String[] urls = new String[]{
            "/employee/login",
            "/employee/logout",
            "/user/login",
            "/user/logout",
            "/backend/**",
            "/front/**",
        };

        //判断本次请求是否需要处理
        boolean check = check(urls, requestURI);

        //如果不需要处理，直接放行
        if (check){
            log.info("本次请求{}不需要处理，直接放行",requestURI);
            chain.doFilter(request,response);
            return;
        }
        //判断登录状态,如果已登录则放行
        if (req.getSession().getAttribute("employee") != null){

            log.info("もうサインしました、idは:{}",req.getSession().getAttribute("employee"));

            Long empId = (Long)req.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);
            chain.doFilter(req,res);
            return;
        }
        if (req.getSession().getAttribute("user")!=null){

            log.info("もうサインしました、idは:{}",req.getSession().getAttribute("user"));

            Long phone = (Long)req.getSession().getAttribute("user");
            BaseContext.setCurrentId(phone);
            chain.doFilter(req,res);
            return;
        }

        log.info("サインしない");
    }


    public boolean check(String[] urls,String requestUrl){
        for(String url: urls){
            boolean match = PATH_MATCHER.match(url,requestUrl);
            if (match){
                return true;
            }
        }
        return false;
    }
}


