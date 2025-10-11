package top.zby.entity.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 书籍视图对象 (View Object)
 * 用于向前端返回书籍的详细信息。
 */
@Data
public class BookVO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 书名。
     */
    private String title;

    /**
     * 作者。
     */
    private String author;

    /**
     * ISBN（国际标准书号）。
     */
    private String isbn;

    /**
     * 分类名称。
     */
    private String category;

    /**
     * 库存数量。
     */
    private Integer stock;

    /**
     * 乐观锁版本号。
     * 备注：返回版本号给前端，以便在后续的更新操作中回传，用于并发控制。
     */
    private Integer version;

    /**
     * 创建时间。
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 最近更新时间。
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
}