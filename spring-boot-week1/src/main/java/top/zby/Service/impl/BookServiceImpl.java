package top.zby.Service.impl;

import org.springframework.stereotype.Service;
import top.zby.DTO.BookDTO;
import top.zby.Service.BookService;

import java.util.List;

/**
 * @author ASUS
 */
@Service
public class BookServiceImpl implements BookService {
    @Override
    public List<BookDTO> getBooks() {
        return List.of(
                new BookDTO(1L, "Spring Boot 2.0", "Bob", 29.99),
                new BookDTO(2L, "Java", "Alice", 34.99)
        );
    }
}
