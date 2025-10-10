package top.zby;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class RedisApplicationTest {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate redisTemplate;
    @Test
    void Test() {

        stringRedisTemplate.opsForValue().set("name","zby");
    }


}