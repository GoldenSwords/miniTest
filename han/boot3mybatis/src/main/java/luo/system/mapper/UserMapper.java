package luo.system.mapper;

import luo.system.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper  {
    @Select("select * from users")
    List<Users> findAll();
    List<Users> getOne();
    @Select("select user_id id,username,password from sys_user")
    List<Users> findAllAdmin();
}
