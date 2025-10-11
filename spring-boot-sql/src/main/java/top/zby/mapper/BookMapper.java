package top.zby.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import top.zby.entity.Book;

@Mapper
public interface BookMapper extends BaseMapper<Book> {

    @Select("select * from book where id = #{id} and deleted = 1")
    Book selectByIdIncludeDeleted(@Param("id") Long id);

    @Update("update book set deleted = #{deleted} where id = #{id}")
    void restore(Book book);
}
