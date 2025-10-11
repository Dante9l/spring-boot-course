package top.zby.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.zby.interceptor.BusinessLogInterceptor;
import top.zby.interceptor.ParamValidateInterceptor;
import top.zby.interceptor.RoleAuthInterceptor;
import top.zby.interceptor.TimeStatInterceptor;

@Slf4j
@Configuration
@AllArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    private final TimeStatInterceptor timeStatInterceptor;
    private final BusinessLogInterceptor businessLogInterceptor;
    private final RoleAuthInterceptor roleAuthInterceptor;
    private final ParamValidateInterceptor paramValidateInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeStatInterceptor)
                .addPathPatterns("/**")
                .order(1);
        registry.addInterceptor(businessLogInterceptor)
                .addPathPatterns("/**")
                .order(2);
        registry.addInterceptor(roleAuthInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/user/login", "/user/register")
                .order(3);
        registry.addInterceptor(paramValidateInterceptor)
                .addPathPatterns("/api/**")
                .order(4);
    }
}
