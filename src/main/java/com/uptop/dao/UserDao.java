package com.uptop.dao;

import com.uptop.entity.Users;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserDao {

    public Users login(Users users);

    public void updateByin(String name);

    public void updateByout(String name);

    public List<Users> searchName();

    public void insertUser(Users users);

    public Users exam(Users users);

    public String seachImages(String name);


}
