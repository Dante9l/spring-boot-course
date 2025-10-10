package top.zby.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Enumeration;

@Slf4j
@Component
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
      log.info("LogFilter: Filter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //预处理
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String url = request.getRequestURI();
        String method = request.getMethod();
        String ip = request.getRemoteAddr();

        //打印请求参数
        StringBuilder params = new StringBuilder();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String names = parameterNames.nextElement();
            String values = request.getParameter(names);
            params.append(names).append("=").append(values).append("&");
        }

        String param = !params.isEmpty() ? params.substring(0, params.length() - 1) : "";

        log.info("请求开始：方法:{} URL:{} IP:{} 参数:{}", method, url, ip, param);

        //放行
        long startTime = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        long endTime = System.currentTimeMillis();

        log.info("请求结束：URL:{} 耗时:{}ms", url, endTime - startTime);
    }

    @Override
    public void destroy() {
        log.info("LogFilter: Filter destroyed");
    }
}
