package top.zby.Service.impl;

import org.springframework.stereotype.Service;
import top.zby.Service.CustomerService;
import top.zby.common.RequestType;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public String handleRequest(RequestType request) {
        return  switch ( request)
                {
                    case QUERY -> handleQuery();
                    case COMPLIANT -> handleCompliant();
                    case SUGGESTION -> handleSuggestion();
                };
    }

    @Override
    public String handleQuery() {
        return "Handling Query...";
    }

    @Override
    public String handleCompliant() {
        return "Handling Compliant...";
    }

    @Override
    public String handleSuggestion() {
        return "Handling Suggestions...";
    }
}
