package top.zby.entity.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

/**
 * 图书入库数据传输对象
 * 用于接收单本书籍增加库存的请求。
 */
@Data
@Getter
public class BookStockDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 要入库的书籍ID (必填)。
     */
    @NotNull(message = "书籍ID不能为空")
    private Long bookId;

    /**
     * 入库数量 (必填, 至少为1)。
     */
    @NotNull(message = "数量不能为空")
    private Integer quantity;

}
