package top.zby.common;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis-Plus 配置类
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 配置并注册 Mybatis-Plus 插件
     * @return MybatisPlusInterceptor 实例
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        // 1. 创建 MybatisPlusInterceptor 拦截器实例
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        // 2. 添加分页插件 (PaginationInnerInterceptor)，并指定数据库类型为 MySQL
        // 分页插件会自动识别数据库类型，您也可以显式指定
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        // 3. 返回配置好的拦截器实例
        return interceptor;
    }
}
