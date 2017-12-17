package luo.system.mapper;

import luo.system.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UrlMapper {
    @Select("selct * from users")
    List<Users> findAll();
}
