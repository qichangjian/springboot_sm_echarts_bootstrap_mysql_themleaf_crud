package com.baizhan.mapper;
import com.baizhan.pojo.Users;

import java.util.List;

/*
    创建mapper接口，以及映射配置文件xml
 */
public interface UsersMapper {
    void insertUser(Users users);
    List<Users> selectUserAll();
    Users selectUsersById(Integer id);
    void updateUser(Users users);
    void deleteUserById(Integer id);
}
