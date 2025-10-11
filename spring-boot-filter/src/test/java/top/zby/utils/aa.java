package top.zby.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc()
@TestPropertySource(properties = {
        "logging.level.org.springframework.web=DEBUG",
        "logging.level.org.springframework.web.servlet.DispatcherServlet=DEBUG",
        "logging.level.org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping=TRACE"
})
class PipelineProbeIT {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ApplicationContext ctx;

    @Test
    void dumpMappingsAndProbe() throws Exception {
        // 1) 打印所有映射
        var hm = ctx.getBean("requestMappingHandlerMapping",
                org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping.class);
        hm.getHandlerMethods().forEach((info, method) ->
                System.out.println("MAPPING: " + info + " -> " + method));

        // 2) 探针请求，观察 print() + 日志
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/api/test"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void listAllFilters() {
        System.out.println("=== Servlet Filter beans (by name) ===");
        ctx.getBeansOfType(jakarta.servlet.Filter.class)
                .forEach((name, bean) -> System.out.println(name + " -> " + bean.getClass().getName()));

        System.out.println("=== FilterRegistrationBean (with order & patterns) ===");
        ctx.getBeansOfType(org.springframework.boot.web.servlet.FilterRegistrationBean.class)
                .forEach((name, frb) -> {
                    System.out.println(name + " -> " + frb.getFilter().getClass().getName()
                            + " order=" + frb.getOrder()
                            + " urlPatterns=" + frb.getUrlPatterns());
                });
    }
}
