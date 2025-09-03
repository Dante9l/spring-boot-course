package top.zby.Service;

import top.zby.common.RequestType;

public interface CustomerService {
    String handleRequest(RequestType request);
    String handleQuery();
    String handleCompliant();
    String handleSuggestion();
}
