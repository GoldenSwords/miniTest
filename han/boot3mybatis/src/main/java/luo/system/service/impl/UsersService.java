package luo.system.service.impl;

import luo.system.dao.UserRepo;
import luo.system.entity.Users;
import luo.system.service.UsersManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService implements UsersManager{
    @Autowired
    UserRepo userRepo;
    @Override
    public List<Users> findAll() {
        return userRepo.findAll();
    }

    @Override
    public List<Users> getOne() {
        return userRepo.getOne();
    }

    @Override
    public List<Users> findAllAdmin() {
        return userRepo.findAllAdmin();
    }
}
