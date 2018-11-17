package com.baizhan.service;

import com.baizhan.mapper.UsersMapper;
import com.baizhan.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *  创建业务层
 */
@Service
@Transactional //表示在这个类中的所有方法都受到事务控制
public class UsersServiceImpl implements UsersService{
    //调用mapper中的方法来完成数据的录入，所以录入mapper接口的代理对象
    @Autowired(required=false)
    private UsersMapper usersMapper;

    @Override
    public void addUser(Users users) {
        System.out.println("进入service:" + users.getName());
        this.usersMapper.insertUser(users);
    }

    @Override
    public List<Users> selectUserAll() {
        return this.usersMapper.selectUserAll();
    }

    @Override
    public Users selectUsersById(Integer id) {
        return this.usersMapper.selectUsersById(id);
    }

    @Override
    public void updateUser(Users users) {
        this.usersMapper.updateUser(users);
    }

    @Override
    public void deleteUserById(Integer id) {
        this.usersMapper.deleteUserById(id);
    }


}
