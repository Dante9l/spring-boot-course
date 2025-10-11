package top.zby.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.zby.filter.CustomCorsFilter;
import top.zby.filter.LogFilter;
import top.zby.filter.RateLimitFilter;

import java.util.List;

@Configuration
@AllArgsConstructor
public class FilterConfig {
    private final LogFilter logFilter;
    private final RateLimitFilter rateLimitFilter;
    private final CustomCorsFilter customCorsFilter;

    @Bean
    public FilterRegistrationBean<RateLimitFilter> rateLimitFilterFilterRegistrationBean() {
        FilterRegistrationBean<RateLimitFilter> filterRegistrationBean = new FilterRegistrationBean();
        // 设置过滤器
        filterRegistrationBean.setFilter(rateLimitFilter);
        // 设置过滤器的url模式
        filterRegistrationBean.addUrlPatterns("/api/pay/*", "/api/order");
        // 设置过滤器的名称
        filterRegistrationBean.setName("rateLimitFilter");
        // 设置过滤器的顺序
        filterRegistrationBean.setOrder(2);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<LogFilter> logFilterFilterRegistrationBean() {
        FilterRegistrationBean<LogFilter> filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(logFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("LogFilter");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.css,*.png");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<CustomCorsFilter> corsFilterFilterRegistrationBean() {
        FilterRegistrationBean<CustomCorsFilter> filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(customCorsFilter);
        filterRegistrationBean.setUrlPatterns(List.of("/*"));
        filterRegistrationBean.setOrder(0);
        return filterRegistrationBean;
    }
}