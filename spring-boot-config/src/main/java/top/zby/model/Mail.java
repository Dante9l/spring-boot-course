package top.zby.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Mail {
    @Email
    @NotBlank
    private String to;
    @NotBlank
    private String subject;
    @NotBlank
    private String content;
}
