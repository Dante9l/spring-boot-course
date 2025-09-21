package top.zby.entity.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

/**
 * 新增书籍DTO
 * 用于接收前端创建新书籍时传递的数据。
 */
@Data
public class BookCreateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 书名（必填）。
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
     * ISBN（国际标准书号）。
     */
    @Size(max = 32, message = "ISBN长度不能超过32个字符")
    private String isbn;

    /**
     * 分类名称或标签。
     */
    @Size(max = 64, message = "分类名称长度不能超过64个字符")
    private String category;

    /**
     * 库存数量（必填）。
     */
    @NotNull(message = "库存数量不能为空")
    @Min(value = 0, message = "库存数量不能为负数")
    private Integer stock;
}