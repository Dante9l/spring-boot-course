package top.zby.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.zby.common.PageResult;
import top.zby.entity.Book;
import top.zby.entity.DTO.BookCreateDTO;
import top.zby.entity.DTO.BookPageQueryDTO;
import top.zby.entity.DTO.BookStockDTO;
import top.zby.entity.DTO.BookUpdateDTO;
import top.zby.entity.VO.BookVO;
import top.zby.enums.ErrorCode;
import top.zby.exception.myException;
import top.zby.mapper.BookMapper;
import top.zby.service.BookService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Resource
    private final BookMapper bookMapper;

    @Override
    public BookVO addBook(BookCreateDTO bookDTO) {

        Book book = new Book();
        BeanUtils.copyProperties(bookDTO, book);
        
        bookMapper.insert(book);

        BookVO bookVO = new BookVO();
        BeanUtils.copyProperties(book, bookVO);
        return bookVO;
    }

    @Override
    public BookVO updateBook(BookUpdateDTO bookDTO) {
        Book book = bookMapper.selectById(bookDTO.getId());
        if (book == null) {
            throw new myException(ErrorCode.NOT_FOUND);
        }

        book.setTitle(bookDTO.getTitle())
                .setAuthor(bookDTO.getAuthor())
                .setIsbn(bookDTO.getIsbn())
                .setCategory(bookDTO.getCategory())
                .setStock(bookDTO.getStock());

        int rows = bookMapper.updateById(book);

        if (rows == 0) {
            throw new myException(ErrorCode.UPDATE_FAIL);
        }

        BookVO bookVO = new BookVO();
        BeanUtils.copyProperties(book, bookVO);
        return bookVO;
    }

    @Override
    public BookVO adjustStock(BookStockDTO bookDTO) {
        Book book = bookMapper.selectById(bookDTO.getBookId());
        if (book == null) {
            throw new myException(ErrorCode.NOT_FOUND);
        }

        // 判断是否为减少库存操作且库存不足
        if (bookDTO.getQuantity() < 0 && book.getStock() < Math.abs(bookDTO.getQuantity())) {
            throw new myException(ErrorCode.BAD_REQUEST);
        }

        int newStock = book.getStock() + bookDTO.getQuantity();
        // 确保调整后库存不为负数
        if (newStock < 0) {
            throw new myException(ErrorCode.BAD_REQUEST);
        }

        book.setStock(newStock);
        bookMapper.updateById(book);

        BookVO bookVO = new BookVO();
        BeanUtils.copyProperties(book, bookVO);
        return bookVO;
    }

    @Override
    public boolean checkIsbn(String isbn) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("isbn", isbn);
        Book book = bookMapper.selectOne(queryWrapper);
        if (book == null) {
            return false;
        }else {
            return true;
        }
    }

    @Override
    public BookVO getBook(Long id) {
        Book book = bookMapper.selectById(id);
        if (book == null) {
            throw new myException(ErrorCode.NOT_FOUND);
        }

        BookVO bookVO = new BookVO();
        BeanUtils.copyProperties(book, bookVO);
        return bookVO;
    }

    @Override
    public PageResult<BookVO> getBooksPage(BookPageQueryDTO bookPageQueryDTO) {
        Page<Book> page = new Page<>(bookPageQueryDTO.getCurrent(), bookPageQueryDTO.getSize());
        LambdaQueryWrapper<Book> queryWrapper = new LambdaQueryWrapper<>();

        //默认按创建时间降序
        if ("asc".equalsIgnoreCase(bookPageQueryDTO.getOrder())){
            queryWrapper.orderByAsc(Book::getCreateTime);
        }else  /*if("desc".equalsIgnoreCase(bookPageQueryDTO.getOrder()))*/{
            queryWrapper.orderByDesc(Book::getCreateTime);
        }

        if (bookPageQueryDTO.getStock() != null) {
            if (bookPageQueryDTO.getStock()) {
                queryWrapper.gt(Book::getStock, 0);
            } else {
                queryWrapper.eq(Book::getStock, 0);
            }
        }

        queryWrapper.like(Book::getTitle, bookPageQueryDTO.getTitle())
                .like(Book::getAuthor, bookPageQueryDTO.getAuthor())
                .like(Book::getCategory, bookPageQueryDTO.getCategory());

        IPage<Book> result = bookMapper.selectPage(page, queryWrapper);

        List<BookVO> voList = result.getRecords().stream().map(book -> {
            BookVO bookVO = new BookVO();

            BeanUtils.copyProperties(book, bookVO);
            return bookVO;

        }).toList();

        return new PageResult().setCurrent(bookPageQueryDTO.getCurrent().intValue())
                .setSize(result.getSize())
                .setTotal(result.getTotal())
                .setData(voList);
    }

    @Override
    public BookVO restoreBook(Long id) {
        Book book = bookMapper.selectByIdIncludeDeleted(id);
        if (book == null) {
            throw new myException(ErrorCode.NOT_FOUND);
        }

        book.setDeleted(0);
        bookMapper.restore(book);
        BookVO bookVO = new BookVO();
        BeanUtils.copyProperties(book, bookVO);
        return bookVO;
    }
}
