package top.zby.entity;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class StudentTest {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void test() {

        Address address = Address.builder()
                .province("广东")
                .city("深圳")
                .build();

        Student student = Student.builder()
                .id(1)
                .name("张三")
                .address(address)
                .build();
        redisTemplate.opsForValue().set("stu1", student,10, TimeUnit.SECONDS);
    }
}