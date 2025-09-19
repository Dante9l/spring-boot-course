package top.zby.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.zby.common.ApiResponse;
import top.zby.entity.UserAccount;
import top.zby.service.UserAccountService;

import java.util.List;

/**
 * 用户账户管理的 RESTful API 控制器
 *
 * @RestController: 组合了 @Controller 和 @ResponseBody，表示该类所有方法返回的数据直接写入响应体（通常是JSON格式）。
 * @RequestMapping("/api/users"): 定义该控制器下所有API的URL前缀。
 * @RequiredArgsConstructor: Lombok注解，为所有 final 字段生成一个构造函数，用于依赖注入。
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserAccountController {

    // 通过构造函数注入 UserAccountService
    private final UserAccountService userAccountService;

    /**
     * 创建单个用户
     * @param userAccount 包含用户信息的请求体，@Valid会触发实体类中的校验规则。
     * @return ApiResponse 包含操作结果和创建后的用户信息。
     */
    @PostMapping
    public ApiResponse<UserAccount> createUser(@Valid @RequestBody UserAccount userAccount) {
        boolean saved = userAccountService.createUser(userAccount);
        return saved
                ? ApiResponse.success("用户创建成功", userAccount)
                : ApiResponse.error("用户创建失败");
    }

    /**
     * 批量创建用户
     * @param userAccounts 用户列表
     * @return ApiResponse 包含操作结果。
     */
    @PostMapping("/batch")
    public ApiResponse<List<UserAccount>> createUsers(@Valid @RequestBody List<UserAccount> userAccounts) {
        boolean saved = userAccountService.createUsers(userAccounts);
        return saved
                ? ApiResponse.success("批量创建成功", userAccounts)
                : ApiResponse.error("批量创建失败");
    }

    /**
     * 根据ID获取单个用户
     * @param id 用户ID，从URL路径中获取。
     * @return ApiResponse 包含查询到的用户信息。
     */
    @GetMapping("/{id}")
    public ApiResponse<UserAccount> getUserById(@PathVariable @NotNull Long id) {
        UserAccount user = userAccountService.getById(id);
        return user != null
                ? ApiResponse.success("查询成功", user)
                : ApiResponse.error("未找到用户");
    }

    /**
     * 获取所有用户列表
     * @return ApiResponse 包含所有用户的列表。
     */
    @GetMapping
    public ApiResponse<List<UserAccount>> getAllUsers() {
        List<UserAccount> users = userAccountService.list();
        return ApiResponse.success("查询成功", users);
    }

    /**
     * 分页查询用户列表
     * @param current 当前页码，默认为 1。
     * @param size 每页显示数量，默认为 10。
     * @return ApiResponse 包含分页信息和当前页的用户数据。
     */
    @GetMapping("/page")
    public ApiResponse<IPage<UserAccount>> getUsersPage(
            @RequestParam(defaultValue = "1") int current,
            @RequestParam(defaultValue = "10") int size) {
        Page<UserAccount> page = new Page<>(current, size);
        IPage<UserAccount> result = userAccountService.page(page);
        return ApiResponse.success("分页查询成功", result);
    }

    /**
     * 根据唯一用户名查询用户
     * @param username 用户名
     * @return ApiResponse 包含查询到的用户信息。
     */
    @GetMapping("/username/{username}")
    public ApiResponse<UserAccount> getUserByUsername(@PathVariable String username) {
        // 使用 LambdaQueryWrapper 构建查询条件，可避免硬编码字段名，更安全
        LambdaQueryWrapper<UserAccount> queryWrapper = new LambdaQueryWrapper<UserAccount>()
                .eq(UserAccount::getUsername, username);
        UserAccount user = userAccountService.getOne(queryWrapper);
        return user != null
                ? ApiResponse.success("查询成功", user)
                : ApiResponse.error("未找到用户");
    }

    /**
     * 根据唯一邮箱查询用户
     * @param email 邮箱
     * @return ApiResponse 包含查询到的用户信息。
     */
    @GetMapping("/email/{email}")
    public ApiResponse<UserAccount> getUserByEmail(@PathVariable String email){
        LambdaQueryWrapper<UserAccount> queryWrapper = new LambdaQueryWrapper<UserAccount>()
                .eq(UserAccount::getEmail, email);
        UserAccount user = userAccountService.getOne(queryWrapper);
        return user != null
                ? ApiResponse.success("查询成功", user)
                : ApiResponse.error("未找到用户");
    }

    /**
     * 根据状态查询用户列表
     * @param status 状态值 (1-启用, 0-禁用)
     * @return ApiResponse 包含符合条件的用户列表。
     */
    @GetMapping("/status/{status}")
    public ApiResponse<List<UserAccount>> getUsersByStatus(@PathVariable Integer status) {
        LambdaQueryWrapper<UserAccount> queryWrapper = new LambdaQueryWrapper<UserAccount>()
                .eq(UserAccount::getStatus, status);
        List<UserAccount> users = userAccountService.list(queryWrapper);
        return ApiResponse.success("查询成功", users);
    }

    /**
     * 根据昵称模糊搜索用户
     * @param nickname 搜索关键词
     * @return ApiResponse 包含匹配到的用户列表。
     */
    @GetMapping("/search")
    public ApiResponse<List<UserAccount>> searchUsersByNickname(@RequestParam String nickname) {
        LambdaQueryWrapper<UserAccount> queryWrapper = new LambdaQueryWrapper<UserAccount>()
                .like(UserAccount::getNickname, nickname);
        List<UserAccount> users = userAccountService.list(queryWrapper);
        return ApiResponse.success("查询成功", users);
    }

    /**
     * 更新用户信息
     * @param id 要更新的用户ID。
     * @param userAccount 包含更新信息的用户对象。
     * @return ApiResponse 包含操作结果。
     */
    @PutMapping("/{id}")
    public ApiResponse<UserAccount> updateUser(@PathVariable @NotNull Long id, @Valid @RequestBody UserAccount userAccount) {
        userAccount.setId(id); // 确保更新的是路径中指定ID的用户
        boolean updated = userAccountService.updateById(userAccount);
        return updated
                ? ApiResponse.success("更新成功", userAccount)
                : ApiResponse.error("更新失败");
    }

    /**
     * 启用/禁用用户账户 (局部更新)
     * @param id 用户ID
     * @param status 目标状态 (0或1)
     * @return ApiResponse 包含操作结果和更新后的用户信息。
     */
    @PatchMapping("/{id}/status")
    public ApiResponse<UserAccount> updateUserStatus(@PathVariable @NotNull Long id, @RequestParam Integer status) {
        // 先查询，再更新，是局部更新的常见做法
        UserAccount user = userAccountService.getById(id);
        if (user != null) {
            user.setStatus(status);
            boolean updated = userAccountService.updateById(user);
            return updated
                    ? ApiResponse.success("状态更新成功", user)
                    : ApiResponse.error("状态更新失败");
        }
        return ApiResponse.error("用户不存在");
    }

    /**
     * 根据ID逻辑删除用户
     * @param id 用户ID
     * @return ApiResponse 包含操作结果。
     */
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable @NotNull Long id) {
        // Mybatis-Plus的removeById会触发@TableLogic逻辑删除
        boolean deleted = userAccountService.removeById(id);
        return deleted
                ? ApiResponse.success("删除成功", null)
                : ApiResponse.error("删除失败或用户不存在");
    }

    /**
     * 根据ID列表批量逻辑删除用户
     * @param ids 用户ID列表
     * @return ApiResponse 包含操作结果。
     */
    @DeleteMapping("/batch")
    public ApiResponse<Void> deleteUsers(@RequestBody List<Long> ids) {
        boolean deleted = userAccountService.removeByIds(ids);
        return deleted
                ? ApiResponse.success("批量删除成功", null)
                : ApiResponse.error("批量删除失败");
    }

    /**
     * 获取用户总数
     * @return ApiResponse 包含用户总数。
     */
    @GetMapping("/count")
    public ApiResponse<Long> getUserCount() {
        long count = userAccountService.count();
        return ApiResponse.success("统计成功", count);
    }

    /**
     * 获取活跃用户数量 (状态为1)
     * @return ApiResponse 包含活跃用户数。
     */
    @GetMapping("/count/active")
    public ApiResponse<Long> getActiveUserCount() {
        LambdaQueryWrapper<UserAccount> queryWrapper = new LambdaQueryWrapper<UserAccount>()
                .eq(UserAccount::getStatus, 1);
        long count = userAccountService.count(queryWrapper);
        return ApiResponse.success("统计成功", count);
    }
}
