package top.zby.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zby.common.ApiResponse;
import top.zby.enums.Drink;

import java.util.Map;

/**
 * @author ASUS
 */
@RestController
@RequestMapping("/drink")
@Slf4j
public class DrinkController {
    @GetMapping("/{type}")
    public ResponseEntity<ApiResponse<Map<String, Object>>> sell(@PathVariable Drink type) {
        Map<String, Object> data = Map.of("type", type.getLabel(), "price", type.getPrice());
        ApiResponse<Map<String, Object>> response = ApiResponse.success("成功", data);
        return ResponseEntity.ok(response);
    }
}
