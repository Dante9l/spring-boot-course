package top.zby.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.zby.common.ApiResponse;
import top.zby.common.PageResult;
import top.zby.entity.DTO.BookCreateDTO;
import top.zby.entity.DTO.BookPageQueryDTO;
import top.zby.entity.DTO.BookStockDTO;
import top.zby.entity.DTO.BookUpdateDTO;
import top.zby.entity.VO.BookVO;
import top.zby.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Resource
    private BookService bookService;

    @PostMapping
    public ApiResponse<BookVO> createBook(@RequestBody BookCreateDTO book) {
        BookVO bookVO = bookService.addBook(book);
        return ApiResponse.success("创建成功", bookVO);
    }

    @PutMapping
    public ApiResponse<BookVO> updateBook(@RequestBody BookUpdateDTO book) {
        BookVO bookVO = bookService.updateBook(book);
        return ApiResponse.success("更新成功", bookVO);
    }

    @PostMapping("{id}/stock/adjust")
    public ApiResponse<BookVO> adjustStock(@RequestBody BookStockDTO book) {
        BookVO bookVO = bookService.adjustStock(book);
        return ApiResponse.success("库存调整成功", bookVO);
    }

    @GetMapping("/{id}")
    public ApiResponse<BookVO> getBook(@PathVariable Long id) {
        BookVO bookVO = bookService.getBook(id);
        return ApiResponse.success("查询成功", bookVO);
    }

    @GetMapping("exists/isbn/{isbn}")
    public ApiResponse<Boolean> checkIsbn(@PathVariable String isbn) {
        boolean exists = bookService.checkIsbn(isbn);
        return ApiResponse.success("查询成功", exists);
    }

    @GetMapping("page")
    public ApiResponse<PageResult<BookVO>> getBooksPage(@RequestBody BookPageQueryDTO bookPageQueryDTO) {
        PageResult<BookVO> bookVOPageResult = bookService.getBooksPage(bookPageQueryDTO);
        return ApiResponse.success("查询成功", bookVOPageResult);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteBook(@PathVariable Long id) {
        boolean b = bookService.removeById(id);
        if (!b) {
            return ApiResponse.error("删除失败或用户不存在");
        }
        return ApiResponse.success("删除成功");
    }

    @PutMapping("/{id}/restore")
    public ApiResponse<BookVO> restoreBook(@PathVariable Long id) {
        BookVO bookVO = bookService.restoreBook(id);
        return ApiResponse.success("更新成功", bookVO);
    }
}