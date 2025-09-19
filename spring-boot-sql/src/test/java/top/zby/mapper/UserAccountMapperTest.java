package top.zby.mapper;

import top.zby.entity.UserAccount;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserAccountMapperTest {
    @Resource
    private UserAccountMapper mapper;

    @Test
    void testSelectById() {
        UserAccount userAccount = mapper.selectById(1L);
        System.out.println(userAccount);
    }
}