package top.zby.model;

import jakarta.validation.constraints.Email;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Mail {
    @Email
    private String to;
    private String subject;
    private String content;
}
