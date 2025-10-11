package top.zby.Service;

import top.zby.entity.LoginRequest;
import top.zby.entity.LoginResponse;


public interface LoginService {

    LoginResponse login(LoginRequest request);
}
