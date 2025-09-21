package top.zby.mapper;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.zby.common.DataFakerUtil;

@SpringBootTest
public class DataFackerUtilTest {

    @Resource
    private DataFakerUtil dataFakerUtil;

    @Test
    void generateBatch() {
        dataFakerUtil.generateBatch();
    }
}
