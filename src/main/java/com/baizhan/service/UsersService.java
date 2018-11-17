package com.baizhan.service;

import com.baizhan.pojo.Users;

import java.util.List;

public interface UsersService {
    void addUser(Users users);
    List<Users> selectUserAll();
    Users selectUsersById(Integer id);
    void updateUser(Users users);
    void deleteUserById(Integer id);
}
