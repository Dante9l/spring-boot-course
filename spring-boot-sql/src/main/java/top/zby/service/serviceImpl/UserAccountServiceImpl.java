package top.zby.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.zby.entity.UserAccount;
import top.zby.mapper.UserAccountMapper;
import top.zby.service.UserAccountService;

import java.util.List;

/**
 * 用户账户服务实现类
 *
 * @Service 标记这是一个 Spring 的 Service 组件.
 * @Transactional 为该类所有公共方法开启事务管理.
 */
@Service
@Transactional
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

    /**
     * 使用 BCrypt 算法进行密码加密的编码器.
     * 定义为静态常量，避免重复创建实例，提高性能.
     */
    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    /**
     * 创建单个用户.
     *
     * @param user 要创建的用户账户对象.
     * @return boolean 操作是否成功.
     */
    @Override
    public boolean createUser(UserAccount user) {
        // 在保存前，对用户数据进行预处理（如加密密码、设置默认值）
        processUserBeforeSave(user);
        // 调用 Mybatis-Plus 提供的 save 方法将用户存入数据库
        return this.save(user);
    }

    /**
     * 批量创建用户.
     *
     * @param users 要创建的用户账户列表.
     * @return boolean 操作是否成功.
     */
    @Override
    public boolean createUsers(List<UserAccount> users) {
        // 遍历列表，对每个用户对象进行预处理
        users.forEach(this::processUserBeforeSave);
        // 调用 Mybatis-Plus 提供的批量保存方法，指定每 1000 条数据为一批进行插入，以提高性能
        return this.saveBatch(users, 1000);
    }

    /**
     * 保存用户前的公共处理逻辑.
     * 这是一个私有辅助方法，用于封装创建用户时的通用步骤.
     *
     * @param user 用户账户对象.
     */
    private void processUserBeforeSave(UserAccount user) {
        // 1. 密码加密处理
        // 检查密码字段是否存在，并且是否尚未被 BCrypt 加密 (BCrypt 哈希值通常以 "$2a$" 或 "$2b$" 等开头)
        if (user.getPassword() != null && !user.getPassword().startsWith("$2")) {
            user.setPassword(ENCODER.encode(user.getPassword()));
        }

        // 2. 设置默认值 - 逻辑删除标记
        // 如果外部没有传入 deleted 值，则默认为 0 (代表未删除)
        if (user.getDeleted() == null) {
            user.setDeleted(0);
        }

        // 3. 设置默认值 - 账户状态
        // 如果外部没有传入 status 值，则默认为 1 (代表启用)
        if (user.getStatus() == null) {
            user.setStatus(1);
        }

        // 4. 设置默认值 - 乐观锁版本号
        // 如果外部没有传入 version 值，则默认为 0 (初始版本)
        if (user.getVersion() == null) {
            user.setVersion(0);
        }
    }
}
