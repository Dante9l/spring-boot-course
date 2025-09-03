package top.zby.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zby.DTO.BookDTO;
import top.zby.Service.BookService;

import java.util.List;

/**
 * @author ASUS
 */
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping("/getBooks")
    public List<BookDTO> getBooks()
        {
            return bookService.getBooks();
        }
}
