package top.zby.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class JwtAuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (token == null || token.isEmpty()){
            response.setStatus(401);
            response.getWriter().write("{\"code\":401,\"msg\":}");
            return false;
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
