package com.uptop.service.serviceImpl;

import com.uptop.dao.UserDao;
import com.uptop.entity.Users;
import com.uptop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService{


    @Autowired
    private UserDao userDao;

    @Override
    public Users login(Users users) {
        return userDao.login(users);
    }

    @Override
    public void updateByin(String name) {
        userDao.updateByin(name);
    }

    @Override
    public void updateByout(String name) {
       userDao.updateByout(name);
    }

    @Override
    public List<Users> searchName() {
        return userDao.searchName();
    }

    @Override
    public void insertUser(Users users) {
        userDao.insertUser(users);
    }

    @Override
    public Users exam(Users users) {
        return userDao.exam(users);
    }

    @Override
    public String seachImages(String name) {
        return userDao.seachImages(name);
    }

}
