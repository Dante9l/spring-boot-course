package top.zby.Service.Impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.zby.Service.LoginService;
import top.zby.cache.RedisCache;
import top.zby.cache.RedisKeys;
import top.zby.entity.LoginRequest;
import top.zby.entity.LoginResponse;
import top.zby.enums.ErrorCode;
import top.zby.exception.myException;

import java.util.UUID;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private RedisCache redisCache;
    @Override
    public LoginResponse login(LoginRequest request) {
        if (request == null || request.getPhone() == null || request.getCode() == null) {
            throw new myException(ErrorCode.BAD_REQUEST);
        }

        String value = redisCache.get(RedisKeys.getSmsKey(request.getPhone())).toString();

        if (value == null || !value.equals(request.getCode())) {
            throw new myException(ErrorCode.SMS_CODE_ERROR);
        }

        redisCache.delete(RedisKeys.getSmsKey(request.getPhone()));
        String token = getToken(request.getPhone());
        redisCache.set(RedisKeys.getUserToken(request.getPhone()), token);
        log.info("用户{}登录", request.getPhone());

        return new LoginResponse(token, request.getPhone());
    }

    public String getToken(String  phone) {
        return UUID.randomUUID().toString().replace("-", "") + phone.hashCode();
    }
}
