package top.zby.utils;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class jwtUtilTest {

    @Resource
    JwtUtil jwtUtil;
    @Test
    void generateToken() {

        String token = jwtUtil.generateToken("1", "zby");
        System.out.println(token);
    }

}