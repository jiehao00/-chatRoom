package com.uptop.service;

import com.uptop.entity.Users;


import java.util.List;

public interface UserService {

    public Users login(Users users);

    public void updateByin(String name);

    public void updateByout(String name);

    public List<Users> searchName();

    public void insertUser(Users users);

    public Users exam(Users users);

    public String seachImages(String name);

}
