package luo.system.service;

import luo.system.entity.Users;

import java.util.List;

public interface UsersManager {
    List<Users> findAll();
    List<Users> getOne();
    List<Users> findAllAdmin();
}
