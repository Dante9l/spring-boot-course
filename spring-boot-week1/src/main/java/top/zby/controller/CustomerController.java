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

    @GetMapping("/{request}")
    public String handleRequest(@PathVariable RequestType request)
    {
        return customerService.handleRequest(request);
    }
}
