package top.zby.Service;

import top.zby.DTO.BookDTO;

import java.util.List;

/**
 * @author ASUS
 */
public interface BookService {
    List<BookDTO> getBooks();
}
