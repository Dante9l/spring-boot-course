package top.zby.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ASUS
 */
@Data
@Accessors(chain = true)
@TableName("user_account")
public class UserAccount implements Serializable {

    /**
     * 序列化版本号，用于保证序列化和反序列化过程的兼容性。
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID。
     * @TableId(type = IdType.AUTO): Mybatis-Plus注解，指定这是主键，并且主键策略为数据库ID自增。
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名（唯一，必填）。
     * @NotBlank: JSR 303校验注解，确保值不为null且不为空字符串。
     * @Size: JSR 303校验注解，限制字符串长度。
     */
    @NotBlank(message = "用户名不能为空")
    @Size(max = 50, message = "用户名长度不能超过50")
    private String username;

    /**
     * 密码（存储BCrypt哈希值，不存储明文）。
     */
    @NotBlank(message = "密码不能为空")
    @Size(max = 255, message = "密码长度过长")
    private String password;

    /**
     * 昵称（可空）。
     */
    @Size(max = 50, message = "昵称长度不能超过50")
    private String nickname;

    /**
     * 邮箱（唯一，可空）。
     * @Email: JSR 303校验注解，验证字段是否为合法的邮箱格式。
     */
    @Email(message = "邮箱格式不正确")
    @Size(max = 100, message = "邮箱长度不能超过100")
    private String email;

    /**
     * 手机号（唯一，可空）。
     */
    @Size(max = 20, message = "手机号长度不能超过20")
    private String phone;

    /**
     * 头像URL（可空）。
     */
    @Size(max = 255, message = "头像URL长度不能超过255")
    private String avatarUrl;

    /**
     * 状态：1-启用，0-禁用（默认启用）。
     * @TableField("status"): 当字段名与数据库列名不一致时（如此处驼峰与下划线），Mybatis-Plus会自动转换，也可显式指定。
     * @Min, @Max: JSR 303校验注解，限制数值范围。
     */
    @TableField("status")
    @Min(value = 0)
    @Max(value = 1)
    private Integer status;

    /**
     * 逻辑删除标记：1-已删除，0-未删除。
     * @TableLogic(value = "0", delval = "1"): Mybatis-Plus逻辑删除注解。
     *     - value="0": 指定未删除状态的值。
     *     - delval="1": 指定已删除状态的值。
     *     配置后，执行删除操作会自动变为更新此字段，查询操作会自动过滤掉已删除的数据。
     */
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;

    /**
     * 乐观锁版本号。
     * @Version: Mybatis-Plus乐观锁注解。在执行更新操作时，会携带此版本号，防止并发修改冲突。
     */
    @Version
    private Integer version;

    /**
     * 创建时间。
     * @TableField(fill = FieldFill.INSERT): Mybatis-Plus自动填充注解，在执行插入(INSERT)操作时自动填充该字段。
     * @JsonFormat: Jackson注解，指定API返回时，将LocalDateTime对象格式化为 "yyyy-MM-dd HH:mm:ss" 字符串，并使用东八区时间。
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 更新时间。
     * @TableField(fill = FieldFill.INSERT_UPDATE): 在执行插入(INSERT)和更新(UPDATE)操作时都会自动填充该字段。
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
}