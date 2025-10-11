package top.zby.entity;

import lombok.Data;

@Data
public class LoginRequest {
    private String phone;

    private String code;
}
