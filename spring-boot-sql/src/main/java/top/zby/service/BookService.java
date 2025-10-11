package top.zby.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.zby.common.PageResult;
import top.zby.entity.Book;
import top.zby.entity.DTO.BookCreateDTO;
import top.zby.entity.DTO.BookPageQueryDTO;
import top.zby.entity.DTO.BookStockDTO;
import top.zby.entity.DTO.BookUpdateDTO;
import top.zby.entity.VO.BookVO;

public interface BookService extends IService<Book> {

    BookVO addBook(BookCreateDTO bookDTO);

    BookVO updateBook(BookUpdateDTO bookDTO);

    BookVO adjustStock(BookStockDTO bookDTO);

    BookVO getBook(Long id);

    boolean checkIsbn(String isbn);

    PageResult<BookVO> getBooksPage(BookPageQueryDTO bookPageQueryDTO);

    BookVO restoreBook(Long id);
}
