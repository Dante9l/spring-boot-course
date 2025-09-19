package top.zby.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.zby.entity.UserAccount;

import java.util.List;

public interface UserAccountService extends IService<UserAccount> {
        boolean createUser(UserAccount user);

        boolean createUsers(List<UserAccount> users);
}
