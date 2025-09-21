package top.zby.common;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import top.zby.entity.UserAccount;
import top.zby.service.UserAccountService;

import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;

/**
 * 用户数据生成工具类。
 * <p>
 * 在开发或测试环境中用于批量生成模拟的用户数据。
 * 它利用 java-faker 库生成随机数据，并通过 UserAccountService 将数据持久化到数据库。
 * 为了确保组件的良好实践，此类采用了构造函数注入、日志记录和常量化配置。
 * </p>
 */
@Component
@Slf4j
@RequiredArgsConstructor // 使用 Lombok 自动生成包含 final 字段的构造函数
public final class DataFakerUtil {

    // --- 常量定义 ---

    /**
     * 中文数据生成器 (线程安全)
     */
    private static final Faker ZH_FAKER = new Faker(Locale.CHINA);

    /**
     * 英文数据生成器 (线程安全)
     */
    private static final Faker EN_FAKER = new Faker(Locale.ENGLISH);

    /**
     * 要生成的记录总数
     */
    private static final int TOTAL_RECORDS = 1000;

    /**
     * 数据库每批次插入的记录数
     */
    private static final int BATCH_SIZE = 100;

    /**
     * 用户的默认原始密码
     */
    private static final String RAW_PASSWORD = "123456";

    private static final String ENCODED_PASSWORD = new BCryptPasswordEncoder().encode(RAW_PASSWORD);

    // --- 依赖注入 ---

    /**
     * 用户账户服务，用于数据持久化操作。通过构造函数注入。
     */
    @Resource
    private final UserAccountService userAccountService;

    /**
     * Spring Security 提供的密码编码器，用于加密密码。
     * 注意：此依赖要求在 Spring IoC 容器中已配置好一个 PasswordEncoder Bean。
     * 例如，在配置类中提供 @Bean public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
     */
    //private final PasswordEncoder passwordEncoder;

    /**
     * 预先计算好的加密密码，避免在循环中重复执行加密操作。
     */

    /**
     * 在依赖注入完成后，预先对密码进行编码。
     * PostConstruct 注解确保此方法在构造函数之后、任何业务方法之前执行一次。
     */
//    @PostConstruct
//    public void init() {
//        this.encodedPassword = passwordEncoder.encode(RAW_PASSWORD);
//    }


    /**
     * 生成单个模拟用户账户。
     *
     * @param index 用户的唯一索引，用于确保用户名的唯一性。
     * @return 配置好的 UserAccount 实例。
     */
    private UserAccount generateOne(int index) {
        UserAccount user = new UserAccount();

        // 原始注释提到用户名可能重复，通过拼接索引来保证其唯一性。
        String username = EN_FAKER.internet().username() + index;

        user.setUsername(username);
        // 使用预先加密好的密码
        user.setPassword(this.ENCODED_PASSWORD);
        user.setNickname(ZH_FAKER.name().fullName());
        user.setEmail(username + "@example.com");
        user.setPhone(ZH_FAKER.phoneNumber().cellPhone());
        user.setAvatarUrl(ZH_FAKER.avatar().image());

        // 设置默认状态
        user.setStatus(1);
        user.setDeleted(0);
        user.setVersion(0);

        return user;
    }

    /**
     * 批量生成并保存用户数据。
     * <p>
     * 此方法会根据预设的 {@code TOTAL_RECORDS} 和 {@code BATCH_SIZE} 分批次生成数据，
     * 并调用 {@link UserAccountService#saveBatch(java.util.Collection, int)} 方法进行持久化。
     * 可以在服务启动时通过 {@code CommandLineRunner} 或在测试用例中调用此方法来填充数据库。
     * </p>
     */
    public void generateBatch() {
        log.info("开始批量生成用户数据... 总数: {}, 每批次: {}", TOTAL_RECORDS, BATCH_SIZE);
        long startTime = System.currentTimeMillis();

        for (int start = 0; start < TOTAL_RECORDS; start += BATCH_SIZE) {
            // 确保最后一批不会超出总数
            int end = Math.min(start + BATCH_SIZE, TOTAL_RECORDS);
            log.info("正在生成批次: [{} -> {}]", start, end - 1);

            List<UserAccount> batch = IntStream.range(start, end)
                    .mapToObj(this::generateOne)
                    .toList();

            // 调用 mybatis-plus 等框架的批量保存方法时，第二个参数通常是批次大小。
            // 我们的 `batch` 列表大小已是 BATCH_SIZE，因此将 BATCH_SIZE 作为参数是正确的。
            // 原始代码中 `saveBatch(batch, 1000)` 的第二个参数可能不正确，这里已修正。
            userAccountService.saveBatch(batch, BATCH_SIZE);
        }

        long endTime = System.currentTimeMillis();
        log.info("批量生成用户数据完成。总计 {} 条, 耗时: {} ms", TOTAL_RECORDS, endTime - startTime);
    }
}
