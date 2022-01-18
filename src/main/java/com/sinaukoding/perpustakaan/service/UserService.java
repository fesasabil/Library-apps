package com.sinaukoding.perpustakaan.service;

import com.sinaukoding.perpustakaan.dao.BaseDao;
import com.sinaukoding.perpustakaan.dao.UserDao;
import com.sinaukoding.perpustakaan.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserDao dao;

    @Override
    protected BaseDao<User> getDao() {
        return dao;
    }
}
