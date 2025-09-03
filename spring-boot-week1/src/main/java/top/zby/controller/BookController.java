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
    /**
     * 获取所有图书信息
     *
     * @return 图书信息列表，包含所有图书的DTO对象
     */
    @GetMapping("/getBooks")
    public List<BookDTO> getBooks()
        {
            return bookService.getBooks();
        }

}
