package top.zby.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 图书信息实体类
 *
 * @Data: Lombok注解，自动生成 getter, setter, toString, equals, hashCode 等方法。
 * @Accessors(chain = true): Lombok注解，开启链式调用，方便对象构建。
 * @TableName("book"): Mybatis-Plus注解，指定该实体类对应的数据库表名为 "book"。  
 */
@Data
@Accessors(chain = true)
@Getter
@TableName("book")
public class Book implements Serializable {

    /**
     * 序列化版本号。
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键ID。
     * @TableId(type = IdType.AUTO): 指定该字段为表的主键，并采用数据库ID自增策略。
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 书名（必填）。
     * @NotBlank: 校验注解，确保书名不为空。
     * @Size: 校验注解，限制书名长度。
     */
    @NotBlank(message = "书名不能为空")
    @Size(max = 200, message = "书名长度不能超过200个字符")
    private String title;

    /**
     * 作者。
     */
    @Size(max = 128, message = "作者名称长度不能超过128个字符")
    private String author;

    /**
     * ISBN（国际标准书号，唯一）。
     */
    @Size(max = 32, message = "ISBN长度不能超过32个字符")
    private String isbn;

    /**
     * 分类名称或标签。
     * 备注：如果系统有独立的分类表，建议将此字段改为 categoryId (Long类型) 以建立外键关联。
     */
    @Size(max = 64, message = "分类名称长度不能超过64个字符")
    private String category;

    /**
     * 库存数量。
     * @NotNull: 校验注解，确保库存字段不为null。
     * @Min: 校验注解，确保库存数量不为负数。
     */
    @NotNull(message = "库存数量不能为空")
    @Min(value = 0, message = "库存数量不能为负数")
    private Integer stock;

    /**
     * 逻辑删除标记：0-未删除, 1-已删除。
     * @TableLogic: Mybatis-Plus逻辑删除注解。
     *     - value="0": 指定未删除状态的值。
     *     - delval="1": 指定已删除状态的值。
     */
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;

    /**
     * 乐观锁版本号。
     * @Version: Mybatis-Plus乐观锁注解，用于处理并发更新。
     */
    @Version
    private Integer version;

    /**
     * 创建时间。
     * @TableField(fill = FieldFill.INSERT): 指定在插入时自动填充该字段。
     * @JsonFormat: Jackson注解，API返回时格式化日期，并指定东八区时区。
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 更新时间。
     * @TableField(fill = FieldFill.INSERT_UPDATE): 指定在插入和更新时都自动填充该字段。
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
}
