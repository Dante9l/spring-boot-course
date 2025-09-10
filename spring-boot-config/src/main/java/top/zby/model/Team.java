package top.zby.model;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author ASUS
 */
@Data
@Component
public class Team {
    @NotNull
    @Value("${team.name}")
    private String name;
    @NotNull
    @Value("${team.leader}")
    private String leader;

    @Min(10)
    @Max(50)
    private Integer age;

    @Pattern(regexp = "^1[3-9]\\d{9}$" , message = "手机号码格式错误")
    private String phone;

}
