package top.zby.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zby.Service.CustomerService;
import top.zby.common.RequestType;

/**
 * @author ASUS
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    /**
     * 处理请求的入口方法
     *
     * @param request 请求类型参数，从URL路径中提取
     * @return 返回处理请求后的结果字符串
     */
    @GetMapping("/{request}")
    public String handleRequest(@PathVariable RequestType request)
    {
        return customerService.handleRequest(request);
    }

}
