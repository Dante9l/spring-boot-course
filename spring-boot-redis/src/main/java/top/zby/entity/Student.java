package top.zby.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    @Serial
    private static final long serialVersionUID = 3445276488182869937L;

    private Integer id;
    private String name;
    private Address address;
}
