package top.zby.entity.DTO;

import lombok.Data;
import lombok.Getter;

/**
 * @author ASUS
 */
@Data
@Getter
public class BookPageQueryDTO {
    private Integer current;

    private Integer size;

    private String title;

    private String author;

    private Boolean stock;

    private String category;

    private String order;
}
