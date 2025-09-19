package top.zby.service;

import org.springframework.stereotype.Service;
import top.zby.enums.ErrorCode;
import top.zby.exception.myException;

@Service
public class ExceptionService {
    public void unAuthorized()
    {
        throw new myException(ErrorCode.UNAUTHORIZED);
    }
    public void notPermission()
    {
        throw new myException(ErrorCode.NOT_PERMISSION);
    }
    public void notFound()
    {
        throw new myException(ErrorCode.NOT_FOUND);
    }
}
