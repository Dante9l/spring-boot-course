package top.zby.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class PageResult<T> {
    private Integer current;
    private Long total;
    private Long size;
    private List<T> data;
}
