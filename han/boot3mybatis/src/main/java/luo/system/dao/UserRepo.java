package luo.system.dao;

import luo.system.entity.Users;
import luo.system.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepo {
    @Autowired
    UserMapper userMapper;

    public List<Users> findAll(){
        return userMapper.findAll();
    }
    public List<Users> getOne(){
        return userMapper.getOne();
    }
    public List<Users> findAllAdmin(){
        return userMapper.findAllAdmin();
    };
}
